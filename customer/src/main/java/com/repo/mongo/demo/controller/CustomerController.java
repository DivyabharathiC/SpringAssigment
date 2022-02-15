package com.repo.mongo.demo.controller;

import com.repo.mongo.demo.model.Customer;
import com.repo.mongo.demo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@SpringBootApplication

@RequestMapping("/customerhello")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomer(@RequestParam(required = false) String name) {
        try {
            List<Customer> customers = new ArrayList<>();
            Customer customer = new Customer();

            if (name == null)
                customerService.getAllCustomer().forEach(customers::add);
            else
                customer = customerService.getCustomerByName(name);
            customers.add(customer);

            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/{account}")
    public ResponseEntity<List<Customer>> getCustomerByAccNum(@PathVariable String account) {
        try {
            List<Customer> customers = new ArrayList<>();
            Customer customer = new Customer();
            //Customer customer;

            customer = customerService.getCustomerByAccNum(account);
            customers.add(customer);

            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else{

            return new ResponseEntity<>(customers, HttpStatus.OK);}
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/postcustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {

            Customer _customer = customerService.addCustomer(new Customer(customer.getName(), customer.getCustomerAccNum(),customer.getAge(),customer.getPhoneNum()));
            return new ResponseEntity<>(_customer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
