package com.panzh.stringmatch;


import org.springframework.stereotype.Component;

@Component
public class BookClassificationNameLengthMatch extends BookClassficationNameAbstractMarch {

    @Override
    public boolean match(String str) {

        return true;
    }
}
