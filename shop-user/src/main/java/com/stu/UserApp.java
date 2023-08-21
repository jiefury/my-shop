package com.stu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
@EnableScheduling
@EnableCaching
public class UserApp {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UserApp.class, args);
        while (true) {
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            String username = environment.getProperty("testName");
            TimeUnit.SECONDS.sleep(10);
            System.out.println(username);
        }
    }
}
