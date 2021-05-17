package com.yc.res;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication

public class ResMvcBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResMvcBootApplication.class, args);
    }

}
