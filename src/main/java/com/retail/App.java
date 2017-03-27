package com.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages =
        {"com.retail", "com.retail.model", "com.retail.repository",
        "com.retail.controller","com.retail.config"
        })
public class App {

    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }

}
