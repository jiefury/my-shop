package com.stu.controller;

import com.stu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope
public class UserController {


    @Autowired
    private RestTemplate restTemplate;


    @Value(value = "${username}")
    private String username;
    @Value(value = "${age}")
    private String age;

    @Autowired
    private TestService testService;

    @RequestMapping("/getUsername")
    public String getUsername() {
        testService.getUser();
        String string = restTemplate.getForObject("http://shop-goods/goods/get", String.class, 1);
        return string;
    }
}
