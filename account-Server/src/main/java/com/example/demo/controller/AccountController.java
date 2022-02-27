package com.example.demo.controller;

import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    String accNotFound = "Account Not found for : ";
    @Autowired
    AccountService accountServiceImpl;

    @GetMapping("/accounts")
    public List<Account> getAccount() {
        logger.info("Starting of accounts get request from accounts application");
        return accountServiceImpl.getAccount();
    }

    @PostMapping("/add-account")
    public Account addAccount(@Valid @RequestBody Account account) {
        logger.info("Starting of accounts post request from accounts application");
        Account account1 = new Account(account.getCustomerId(), account.getCustomerAccNum(), account.getAccountBalance(), account.getAccountType(), account.getIsActive());
        return accountServiceImpl.createAccount(account1);
    }


    @GetMapping("/customer-id/{id}")
    public List<Account> getAccountDetailsByCustomerId(@PathVariable("id") Integer id) {
        logger.info("Starting of accounts get request using customerid from accounts application");
        return accountServiceImpl.getAccountDetailsByCustomerId(id);
    }

    @DeleteMapping("/remove-account/{id}")
    public String deleteCustomerAndAccount(@PathVariable("id") Integer id) {
        logger.info("Starting of accounts delete request from accounts application");
        if (!(accountServiceImpl.isActive(id)))
            throw new AccountNotFoundException(accNotFound + id);
        return accountServiceImpl.deleteCustomerAndAccount(id);
    }

}
