package com.panzh.teamindexweb.config.interceptor;


import com.panzh.annotaion.RequireRole;
import com.panzh.entity.UserInfo;
import com.panzh.myconst.UserContst;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author: TMingYi
 * @date: 2020/4/23 17:55
 */
@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    //handler请求的mapper地址；
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //这里是给一个方法加上注解，使用handler进行统一的拦截处理;
        HandlerMethod am = (HandlerMethod) handler;
        RequireRole role = am.getMethodAnnotation(RequireRole.class);
        //表示是公共的方法，不需要进行登录和身份认证;
        if (role == null){
            return true;
        }

        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(UserContst.USER_INFO);

        if (userInfo == null){
            response.sendRedirect("/index");
            return false;
        }

        if (userInfo.getUserPower() >= role.value().getValue()){
            return true;
        }else {
            response.sendRedirect("/index");
            return false;
        }

    }

}
