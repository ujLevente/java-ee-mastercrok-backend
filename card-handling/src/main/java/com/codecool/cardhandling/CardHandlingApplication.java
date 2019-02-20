package com.codecool.cardhandling;

import com.codecool.cardhandling.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class CardHandlingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardHandlingApplication.class, args);
    }

}
