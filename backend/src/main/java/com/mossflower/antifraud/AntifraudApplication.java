package com.mossflower.antifraud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;


/**
 * @author z's'b
 */
@SpringBootApplication
@MapperScan({"com.gitee.sunchenbin.mybatis.actable.dao.*", "com.mossflower.antifraud.mapper"})
@ComponentScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.manager.*", "com.mossflower.antifraud"})
public class AntifraudApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntifraudApplication.class, args);
    }

}
