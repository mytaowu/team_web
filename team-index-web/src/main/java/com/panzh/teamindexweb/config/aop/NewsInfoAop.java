package com.panzh.teamindexweb.config.aop;

import com.alibaba.dubbo.config.annotation.Reference;
import com.panzh.service.NewsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: TMingYi
 * @date: 2020/6/11 10:50
 * aop处理类
 */
@Aspect
@Component
public class NewsInfoAop {

    @Reference
    private NewsService newsService;

    //获取我们的日志处理对象
    Logger logger = LoggerFactory.getLogger(getClass());

    //我们的参数映射器
    private ParameterNameDiscoverer parameterUtil = new LocalVariableTableParameterNameDiscoverer();

    //    基于注解的切面(公共的切面)
    @Pointcut("@annotation(com.panzh.annotaion.AddFindTime)")
    public void pointCut(){};

    //前置方法，这里就可以进行业务逻辑的处理
    @Before("pointCut()")
    public void beforMethod(JoinPoint joinPoint){
        //获得未被代理的原对象，我们这里应该是个Method
        // Method method = (Method) joinPoint.getTarget();
        //这里拿到方法的原信息
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        //获取参数名称列表
        String[] names = parameterUtil.getParameterNames(method);
        //获取参数值的列表
        Object[] args = joinPoint.getArgs();
        //循环找出我们需要的参数值;
        for (int i =  0 ; i < names.length ; i++){
            //获得指定目标的方法;
            if (names[i].equals("newId")){
                //按照道理说，这里应该只有一个参数但是还是要判断一下
                newsService.addFindTime(Integer.parseInt(args[i].toString()));
                break;
            }
        }
    }
}
