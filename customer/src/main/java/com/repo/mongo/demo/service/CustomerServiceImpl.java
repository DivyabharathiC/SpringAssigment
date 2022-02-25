package com.repo.mongo.demo.service;

import com.repo.mongo.demo.feign.AccountFeign;
import com.repo.mongo.demo.model.*;
import com.repo.mongo.demo.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
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
    public CustomerAccountResponseDetails addCustomer(CustomerAccountResponseDetails customerAccountResponseDetails) {

        HttpHeaders header = new HttpHeaders();
        Account account = new Account();
        account.setCustomerId(customerAccountResponseDetails.getCustomer().getCustomerId());
        account.setAccountType(customerAccountResponseDetails.getAccount().getAccountType());
        account.setAccountBalance(customerAccountResponseDetails.getAccount().getAccountBalance());
        account.setIsActive(true);
        customerAccountResponseDetails.setAccount(account);
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Account> httpEntity = new HttpEntity<>(customerAccountResponseDetails.getAccount(), header);
        customerAccountResponseDetails.setAccount(restTemplate.postForObject("http://localhost:8088/account/createaccount", httpEntity, Account.class));
        customerAccountResponseDetails.setCustomer(customerRepo.save(new Customer(customerAccountResponseDetails.getCustomer().getCustomerId(), LocalDate.now(), customerAccountResponseDetails.getCustomer().getCustomerFirstName(), customerAccountResponseDetails.getCustomer().getCustomerLastName(), customerAccountResponseDetails.getCustomer().getPhoneNumber(), customerAccountResponseDetails.getCustomer().getIsCustomerActive(), customerAccountResponseDetails.getCustomer().getCustomerInfoEditedDate(), customerAccountResponseDetails.getCustomer().getCustomerType())));
        return customerAccountResponseDetails;

    }


    @Override
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }


    @Override
    public Customer customerPresent(Integer customerId) {
        return customerRepo.findByCustomerId(customerId);

    }

    @Override
    public Customer findById(Integer id) {
        return customerRepo.findById(id);
    }

    @Override
    public Boolean customerIsActive(Integer customerId) {
        Customer customer = customerRepo.findByCustomerId(customerId);
        if (customer == null) {
            return false;
        }
        return customer.getIsCustomerActive();

    }

    @Override
    public CustomerAllData getAllDataByCustomerId(Integer customerId) {
        Customer customerData = customerRepo.findByCustomerId(customerId);
        List<Account> accounts = accountFeign.getAccountDetailsByCustomerId(customerId);
        return new CustomerAllData(accounts, customerData);

    }

    @Override
    public String deleteCustomer(Integer id) {
        Customer customer = customerRepo.findByCustomerId(id);
        customer.setIsCustomerActive(accountFeign.deleteCustomerAndAccount(id));
        customerRepo.save(customer);
        return "Customer deleted for id : " + id;
    }

    @Override
    public Customer updateCustomer(Integer id, UpdateCusDetails customer) {
        Customer customerUpdate = customerRepo.findByCustomerId(id);
        customerUpdate.setPhoneNumber(customer.getPhoneNumber());
        customerUpdate.setCustomerLastName(customer.getLastName());
        customerRepo.save(customerUpdate);
        return customerUpdate;
    }

}
