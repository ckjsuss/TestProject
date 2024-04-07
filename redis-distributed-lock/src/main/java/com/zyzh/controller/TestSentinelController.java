package com.zyzh.controller;

import com.zyzh.config.SentinelConfiguration;
import com.zyzh.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger = LoggerFactory.getLogger(TestSentinelController.class);
    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        logger.info("name:{}",name);
        return helloService.sayHello(name);
    }

    @GetMapping("/circuitBreaker")
    public String circuitBreaker(@RequestParam("name") String name) {
        return helloService.circuitBreaker(name);
    }


}