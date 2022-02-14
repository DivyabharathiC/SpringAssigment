package com.repo.mongo.demo.service;

import com.repo.mongo.demo.model.Customer;
import com.repo.mongo.demo.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;


    @Override
    public Customer addCustomer(Customer customer) {
         return customerRepo.save(new Customer(customer.getName(), customer.getCustomerAccNum(),customer.getAge(),customer.getPhoneNum() ));

    }

    public Customer getCustomerByName(String name) {
        return customerRepo.findByName(name);
    }

    @Override
    public Customer getCustomerByAccNum(String name) {
        return customerRepo.findByCustomerAccNum(name);
    }


    @Override
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }
}
