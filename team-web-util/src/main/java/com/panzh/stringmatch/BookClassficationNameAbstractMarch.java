package com.panzh.stringmatch;

import com.panzh.funcationInterface.InfomationMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BookClassficationNameAbstractMarch implements InfomationMatch {

    protected InfomationMatch next;


    public void setNext(InfomationMatch next) {
        this.next = next;
    }
}
