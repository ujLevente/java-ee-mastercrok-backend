package com.codecool.gamestatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GameStatusApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameStatusApplication.class, args);
    }

}
