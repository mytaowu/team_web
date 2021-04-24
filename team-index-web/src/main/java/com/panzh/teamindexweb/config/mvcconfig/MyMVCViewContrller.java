package com.panzh.teamindexweb.config.mvcconfig;

import com.panzh.teamindexweb.config.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author: TMingYi
 * @date: 2020/5/15 15:12
 */
@Configuration
public class MyMVCViewContrller extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    //viewController
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //这里写我们的viewController
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/").setViewName("/index");
        registry.addViewController("/index.html").setViewName("/index");
        registry.addViewController("/team_dyname").setViewName("/team_dyname");
        registry.addViewController("/loginpage").setViewName("/login_page");
        registry.addViewController("/learning_resource").setViewName("/learning_resource");
        registry.addViewController("/team_achievements").setViewName("/team_achievements");
        registry.addViewController("/team_news").setViewName("/team_news");
        registry.addViewController("/registerpage").setViewName("/register_page");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**");

        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/show_dy_info.do/image").addResourceLocations("/image");
//        registry.addResourceHandler("/show_dy_info.do/css").addResourceLocations("/css");
//        registry.addResourceHandler("/show_dy_info.do/js").addResourceLocations("/js");
//        registry.addResourceHandler("/show_dy_info.do/layui").addResourceLocations("/layui");
        registry.addResourceHandler("/show_dy_info.do/loginpage").addResourceLocations("loginpage");
    }


    //增加解决跨域问题的代码；


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowCredentials(true)
                //设置一个小时之内不需要在检验
                .maxAge(3600);
    }
}
