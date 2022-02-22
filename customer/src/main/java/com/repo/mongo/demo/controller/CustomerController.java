package com.repo.mongo.demo.controller;

import com.repo.mongo.demo.exception.CustomerIsNotEligibleToDelete;
import com.repo.mongo.demo.exception.CustomerNotFoundException;
import com.repo.mongo.demo.feign.AccountFeign;
import com.repo.mongo.demo.model.Account;
import com.repo.mongo.demo.model.Customer;
import com.repo.mongo.demo.model.RequiredResponse;
import com.repo.mongo.demo.model.UpdateCusDetails;
import com.repo.mongo.demo.repo.CustomerRepo;
import com.repo.mongo.demo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@SpringBootApplication

@RequestMapping("/customerhello")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    AccountFeign accountFeign;
    @Autowired
     RestTemplate restTemplate;

    @Autowired
    CustomerRepo customerRepo;

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

    @GetMapping("/id/{id}")
    public ResponseEntity<Customer> getById(@PathVariable("id") Integer id) {
        Customer one = customerService.findById(id);
        return new ResponseEntity<Customer>(one, HttpStatus.OK);
    }

    @GetMapping("/id/ids/{id}")
    public ResponseEntity<RequiredResponse> getAllDataBasedOnCentreId(@PathVariable("id") Integer id) {
        RequiredResponse requiredResponse = new RequiredResponse();
        Customer cus = customerService.findById(id);
        requiredResponse.setCustomer_model(cus);
        Account accounts = accountFeign.getIds(id);
        requiredResponse.setAccount_model((Account) accounts);
        return new ResponseEntity<RequiredResponse>(requiredResponse, HttpStatus.OK);
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
            } else {

                return new ResponseEntity<>(customers, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/postcustomer")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        try {

            Customer _customer = customerService.addCustomer(new Customer(customer.getCustomerId(), customer.getName(), customer.getCustomerAccNum(), customer.getAge(), customer.getPhoneNum(), customer.getCreatedDate(), customer.getIsActive(), customer.getLastName()));
            System.out.println(_customer);
            return new ResponseEntity<>(_customer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") String id, @Valid @RequestBody UpdateCusDetails customer)
    {
        try {
            if (!customerService.isCustomerPresent(id)) {
                throw new CustomerNotFoundException("Customer is not present in DB : " + id);
            }
        } catch (Exception e)
        {
            return new ResponseEntity<>(customerService.updateCustomer(id, customer), HttpStatus.OK);
        }
        return null;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") String id)
    {
        try {
            if (customerService.isCustomerPresent(id) && !customerService.isCustomerActive(id))
            {
                throw new CustomerIsNotEligibleToDelete("Customer is not eligible to delete :" + id);
            }
        } catch (Exception e)
        {
            return new ResponseEntity<Customer>(customerService.deleteCustomer(id), HttpStatus.OK);
        }
        return null;
    }

}
