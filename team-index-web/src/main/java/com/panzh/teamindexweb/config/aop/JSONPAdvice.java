package com.panzh.teamindexweb.config.aop;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * @author: TMingYi
 * @date: 2020/7/6 10:45
 * 转换为jsonp格式的类
 */

@ControllerAdvice(basePackages="com.panzh.teamindexweb.handler")
public class JSONPAdvice extends AbstractJsonpResponseBodyAdvice{

    public JSONPAdvice() {
        super("callback");
    }
}
