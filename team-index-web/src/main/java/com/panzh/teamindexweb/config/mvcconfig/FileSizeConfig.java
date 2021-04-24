package com.panzh.teamindexweb.config.mvcconfig;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * 上传大图必须要进行配置；
 */
@Configuration
public class FileSizeConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("30240KB"); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }

}
