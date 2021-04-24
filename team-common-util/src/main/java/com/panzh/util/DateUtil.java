package com.panzh.util;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: TMingYi
 * @date: 2020/6/11 9:50
 */

public class DateUtil {

    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static String formatDate(Date date){
        Assert.isNull(date);
        return format.format(date);
    }
}
