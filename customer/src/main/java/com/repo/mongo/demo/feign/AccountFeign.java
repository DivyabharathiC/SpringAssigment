package com.repo.mongo.demo.feign;

import com.repo.mongo.demo.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ACCOUNT-SERVER", fallbackFactory = HystrixFallBackFactory.class)
public interface AccountFeign {

    @GetMapping(value = "/account/customer-id/{id}")
    List<Account> getAccountDetailsByCustomerId(@PathVariable("id") Integer id);


    @DeleteMapping(value = "/account/remove-account/{id}")
    Boolean deleteCustomerAndAccount(@PathVariable("id") Integer id);
}
