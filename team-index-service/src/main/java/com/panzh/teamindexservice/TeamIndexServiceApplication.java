package com.panzh.teamindexservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.panzh.teamindexservice.mapper")
public class TeamIndexServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamIndexServiceApplication.class, args);
    }

}