package com.repo.mongo.demo.service;

import com.repo.mongo.demo.model.Customer;
import com.repo.mongo.demo.model.UpdateCusDetails;

import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer);
    Customer getCustomerByName(String name);
    Customer getCustomerByAccNum(String CustomerAccNum);
    List<Customer> getAllCustomer();
    Customer findById(Integer id);
    boolean isCustomerActive(String customerId);
    boolean isCustomerPresent(String customerId);
    Customer updateCustomer(String id, UpdateCusDetails customer);
    Customer deleteCustomer(String id);

}
