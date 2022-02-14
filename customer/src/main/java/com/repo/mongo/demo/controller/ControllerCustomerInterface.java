package com.repo.mongo.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/hello")
public interface ControllerCustomerInterface {
    @GetMapping("/greeting")
    String greeting();
    @GetMapping("/greeting2")
    String greeting2();
}
