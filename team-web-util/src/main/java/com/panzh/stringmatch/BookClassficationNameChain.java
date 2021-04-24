package com.panzh.stringmatch;

import com.panzh.myconst.ExceptionConst;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookClassficationNameChain implements ApplicationContextAware {

    private BookClassficationNameAbstractMarch bookClassficationNameRootChainCache;

    private ApplicationContext context;

    @Autowired(required = false)
    private Map<String,List<BookClassficationNameAbstractMarch>> stringNameInitSetingMap;

//    public boolean Match(String name){
//        if (bookClassficationNameAbstractMarch == null) return true;
//        if (bookClassficationNameAbstractMarch.size() == 0) return true;
//        if (bookClassficationNameRootChainCache == null){
//            doCreateBookClassficationNameRootChain();
//        }
//        return bookClassficationNameRootChainCache.match(name);
//    }


    public boolean Match(String chainName,String stringName){
        //进行条件判断;
        conditionGudge(stringName);
        List<BookClassficationNameAbstractMarch> marchList = stringNameInitSetingMap.get(chainName);
        if (marchList != null){
            doCreateBookClassficationNameRootChain(marchList);
        }else {
            throwRuntimeException(ExceptionConst.NO_MATCHING_CLASS_FOUND);
        }
        return bookClassficationNameRootChainCache.match(stringName);
    }

    public boolean Match(String chainName,Class<? extends BookClassficationNameAbstractMarch>...matchingClass){

        if (matchingClass ==null){
            throwRuntimeException("matchingClass not null");
        }
        List<BookClassficationNameAbstractMarch> marchList = new ArrayList<>();
        for (Class<? extends BookClassficationNameAbstractMarch> targClass : matchingClass) {
             marchList.add(context.getBean((targClass)));
        }
        doCreateBookClassficationNameRootChain(marchList);
        return bookClassficationNameRootChainCache.match(chainName);
    }



    /**
     * 链接成链
     * @param marchList
     */
    private void doCreateBookClassficationNameRootChain(List<BookClassficationNameAbstractMarch> marchList) {
        boolean flag = true;
        Iterator<BookClassficationNameAbstractMarch> iterator = marchList.iterator();
        bookClassficationNameRootChainCache = marchList.get(0);
        for (int i = 0 ; i < marchList.size() - 1 ; i++){
            marchList.get(i).setNext(marchList.get(i+1));
        }
    }

    private void conditionGudge(String name) {
        if (stringNameInitSetingMap == null){
            throwRuntimeException(ExceptionConst.ARGE_MUST_BY_INIT_STRINGMAP);
        }
        if (stringNameInitSetingMap.size() == 0){
            throwRuntimeException(ExceptionConst.ARGE_MUST_BY_INIT_STRINGMAP);
        }
        if (name == null){
            throwRuntimeException("Illega Must Not Null");
        }
        if (name.length() == 0){
            throwRuntimeException("Illega Must Not Empty");
        }

    }

    private void throwRuntimeException(String noMatchingClassFound) {
        throw new RuntimeException(noMatchingClassFound);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
