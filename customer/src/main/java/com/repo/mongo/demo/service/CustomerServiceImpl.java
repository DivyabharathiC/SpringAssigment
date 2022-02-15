package com.repo.mongo.demo.service;

import com.repo.mongo.demo.model.Customer;
import com.repo.mongo.demo.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Customer addCustomer(Customer customer) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(customer.getCustomerAccNum(), headers);
        String value = restTemplate.postForObject("http://account-Server/account/createaccount", entity, String.class);
        customer.setCustomerAccNum(value);
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
