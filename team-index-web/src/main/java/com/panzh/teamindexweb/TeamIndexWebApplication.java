package com.panzh.teamindexweb;

import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.swing.*;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableSwagger2
@EnableScheduling
@EnableAspectJAutoProxy
@ComponentScan("com.panzh")
public class TeamIndexWebApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(TeamIndexWebApplication.class, args);
    }


}
