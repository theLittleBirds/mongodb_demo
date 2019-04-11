package com.zd.springcludedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaServer
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.zd.*"})
public class SpringcludeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcludeDemoApplication.class, args);
    }

}
