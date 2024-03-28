package com.zyzh.controller;

import com.zyzh.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@RestController
@RequestMapping("/sentinel")
public class TestSentinelController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        return helloService.sayHello(name);
    }

    @GetMapping("/circuitBreaker")
    public String circuitBreaker(@RequestParam("name") String name) {
        return helloService.circuitBreaker(name);
    }


}