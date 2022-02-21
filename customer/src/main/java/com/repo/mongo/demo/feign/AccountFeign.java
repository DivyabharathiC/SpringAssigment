package com.repo.mongo.demo.feign;

import com.repo.mongo.demo.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ACCOUNT-SERVER", fallbackFactory = HystrixFallBackFactory.class)//url="http://localhost:8088")
public interface AccountFeign {
    @GetMapping(value = "/account/id/{id}")
    Account getIds(@PathVariable Integer id);

//    @GetMapping(value = "/account/id/{id}")
//    List<Account> getById(@PathVariable("id") Integer id);
@DeleteMapping(value = "/accounts/account/{id}")
Boolean deleteMappedAcc(@PathVariable String id);
}
