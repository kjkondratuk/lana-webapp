package org.kondrak.lana;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
public class LanaApp {
    public static void main(String[] args) {
        SpringApplication.run(LanaApp.class, args);
    }
}
