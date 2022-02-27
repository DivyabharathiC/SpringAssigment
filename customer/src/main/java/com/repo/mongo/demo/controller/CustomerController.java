package com.repo.mongo.demo.controller;

import com.repo.mongo.demo.exception.CustomerAlreadyExistException;
import com.repo.mongo.demo.exception.CustomerNotFoundException;
import com.repo.mongo.demo.feign.AccountFeign;

import com.repo.mongo.demo.model.*;
import com.repo.mongo.demo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@Slf4j


@RequestMapping("/customer")
public class CustomerController {

    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;
    @Autowired
    AccountFeign accountFeign;


    String custNotFound = "Customer Not Found with : ";
    String custAlreadyExist = "Customer Already exist : ";

    @PostMapping("/add-Customer")
    public ResponseEntity<CustomerAccountResponseDetails> addCustomer(@Valid @RequestBody CustomerAccountResponseDetails customerAccountResponseDetails) {
        logger.info("Starting of customer post request from customer application");
        try {
            Integer customerId = customerAccountResponseDetails.getCustomer().getCustomerId();
            if (customerService.customerPresent(customerId) != null) {
                throw new CustomerAlreadyExistException(custAlreadyExist + customerId);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(customerAccountResponseDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(customerService.addCustomer(customerAccountResponseDetails), HttpStatus.CREATED);//Customer and Account value are send to the addCustomer method
    }


    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        logger.info("Starting of customer get request from customer application");
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);

    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<CustomerAllData> getAllDataByCustomerId(@PathVariable("id") Integer id) {
        logger.info("Starting of customer get request using customerID from customer application");
        if (Boolean.FALSE.equals(customerService.customerIsActive(id))) {
            throw new CustomerNotFoundException(custNotFound + id);
        }

        return new ResponseEntity<>(customerService.getAllDataByCustomerId(id), HttpStatus.FOUND);

    }

    @DeleteMapping("/remove-Customer/{id}")
    public ResponseEntity<String> deleteCustomerDetails(@PathVariable("id") Integer id) {

        logger.info("Starting of customer delete request from customer application");
        if (Boolean.FALSE.equals(customerService.customerIsActive(id))) {
            throw new CustomerNotFoundException(custNotFound + id);
        }
        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }

    @PutMapping("/update-customer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Integer id, @Valid @RequestBody UpdateCusDetails customer) {
        logger.info("Starting of customer put request from customer application");
        if (Boolean.FALSE.equals(customerService.customerIsActive(id))) {
            throw new CustomerNotFoundException(custNotFound + id);
        }
        return new ResponseEntity<>(customerService.updateCustomer(id, customer), HttpStatus.OK);
    }


}
