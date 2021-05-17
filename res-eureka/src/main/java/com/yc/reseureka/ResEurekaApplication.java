package com.yc.reseureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ResEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResEurekaApplication.class, args);
    }

}
