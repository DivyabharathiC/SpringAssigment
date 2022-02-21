package com.repo.mongo.demo.repo;

import com.repo.mongo.demo.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepo extends MongoRepository<Customer, String > {
    Customer findByName(String name);
    Customer findByCustomerAccNum(String name);
    Customer findById(Integer id);
    Customer findByCustomerId(String customerId);
    Customer deleteByCustomerId(String customerId);





}
