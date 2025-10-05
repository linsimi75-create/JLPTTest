package com.ruoyi.newfront;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan("com.ruoyi.newfront.dao.mapper")
public class NewfrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewfrontApplication.class, args);
    }

}
