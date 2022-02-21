package com.repo.mongo.demo.service;

import com.repo.mongo.demo.feign.AccountFeign;
import com.repo.mongo.demo.model.Customer;
import com.repo.mongo.demo.model.UpdateCusDetails;
import com.repo.mongo.demo.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AccountFeign accountFeign;

    @Override
    public Customer addCustomer(Customer customer) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Customer> entity = new HttpEntity<Customer>(customer, headers);
       // String value = restTemplate.postForObject("http://account-Server/account/createaccount", entity, String.class);
       // customer.setCustomerAccNum(value);
         return customerRepo.save(new Customer(customer.getCustomerId(),customer.getName(), customer.getCustomerAccNum(),customer.getAge(),customer.getPhoneNum(),customer.getCreatedDate(),customer.getIsActive(),customer.getLastName() ));

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

    @Override
    public Customer findById(Integer id) {
        return customerRepo.findById(id);
    }

    @Override
    public boolean isCustomerActive(String customerId) {
        Customer customer = customerRepo.findByCustomerId(customerId);
        boolean isCustomerActiveVal = false;
        isCustomerActiveVal=(customer == null)?true:customer.getIsActive();
        return isCustomerActiveVal;
    }

    @Override
    public boolean isCustomerPresent(String customerId) {
        boolean isCustomerPresentValue = false;
        Customer customer = customerRepo.findByCustomerId(customerId);
        if (customer != null) {
            isCustomerPresentValue = true;
        }
        return isCustomerPresentValue;
    }

    @Override
    public Customer updateCustomer(String id, UpdateCusDetails customer) {
        Customer customerUpdate = customerRepo.findByCustomerId(id);
        customerUpdate.setPhoneNum(customer.getPhoneNumber());
        customerUpdate.setLastName(customer.getLastName());
        customerRepo.save(customerUpdate);
        return customerUpdate;
    }

    @Override
    public Customer deleteCustomer(String id) {
        Customer customer = customerRepo.findByCustomerId(id);
        customerRepo.deleteByCustomerId(id);
        customer.setIsActive(accountFeign.deleteMappedAcc(id));
        customerRepo.save(customer);
        return customer;
    }

}
