package com.repo.mongo.demo.service;

import com.repo.mongo.demo.model.Customer;
import com.repo.mongo.demo.model.CustomerAccountResponseDetails;
import com.repo.mongo.demo.model.CustomerAllData;
import com.repo.mongo.demo.model.UpdateCusDetails;

import java.util.List;


public interface CustomerService {

    CustomerAccountResponseDetails addCustomer(CustomerAccountResponseDetails customerAccountResponseDetails);

    List<Customer> getAllCustomer();

    Customer findById(Integer id);

    Customer customerPresent(Integer customerId);

    Boolean customerIsActive(Integer customerId);

    CustomerAllData getAllDataByCustomerId(Integer customerId);

    String deleteCustomer(Integer id);

    Customer updateCustomer(Integer id, UpdateCusDetails customer);

}
