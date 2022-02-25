package com.example.demo.controller;

import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    String accNotFound = "Account Not found for : ";
    @Autowired
    AccountService accountServiceImpl;

    @GetMapping("/getaccount")
    public List<Account> getAccount() {
        return accountServiceImpl.getAccount();
    }

    @PostMapping("/createaccount") //this method is only called from customer application
    public Account addAccount(@Valid @RequestBody Account account) {
        Account account1 = new Account(account.getCustomerId(), account.getCustomerAccNum(), account.getAccountBalance(), account.getAccountType(), account.getIsActive());
        return accountServiceImpl.createAccount(account1);
    }


    @GetMapping("/customerid/{id}")
    public List<Account> getAccountDetailsByCustomerId(@PathVariable("id") Integer id) {
        return accountServiceImpl.getAccountDetailsByCustomerId(id);
    }

    @DeleteMapping("/deleteCustomerAndAccount/{id}")// this method is called from customer application
    public String deleteCustomerAndAccount(@PathVariable("id") Integer id) {
        if (!(accountServiceImpl.isActive(id)))
            throw new AccountNotFoundException(accNotFound + id);
        return accountServiceImpl.deleteCustomerAndAccount(id);
    }

}
