package com.repo.mongo.demo.service;

import com.repo.mongo.demo.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer);
    Customer getCustomerByName(String name);
    Customer getCustomerByAccNum(String CustomerAccNum);
    List<Customer> getAllCustomer();

}
