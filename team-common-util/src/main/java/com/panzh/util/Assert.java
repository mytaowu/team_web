package com.panzh.util;

/**
 * @author: TMingYi
 * @date: 2020/6/11 9:52
 */
public class Assert {

    public static void isNull(Object o){
        if (o == null){
            new RuntimeException("Parameter cannot be null");
        }
    }
}
