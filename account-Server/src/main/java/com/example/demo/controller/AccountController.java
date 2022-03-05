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

import static com.example.demo.constant.Constant.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountServiceImpl;

    @GetMapping(GetAll_AccountData)
    public List<Account> getAccount() {
        logger.info("Starting of accounts get request from accounts application");
        return accountServiceImpl.getAccount();
    }

    @PostMapping(Add_AccountData)
    public Account addAccount(@Valid @RequestBody Account account) {
        logger.info("Starting of accounts post request from accounts application");
        Account account1 = new Account(account.getCustomerId(), account.getCustomerAccNum(), account.getAccountBalance(), account.getAccountType(), account.getIsActive());
        return accountServiceImpl.createAccount(account1);
    }


    @GetMapping(Get_AccountByCustomerID)
    public List<Account> getAccountDetailsByCustomerId(@PathVariable("id") Integer id) {
        logger.info("Starting of accounts get request using customerid from accounts application");
        return accountServiceImpl.getAccountDetailsByCustomerId(id);
    }

    @DeleteMapping(Delete_AccountByCustomerID)
    public String deleteCustomerAndAccount(@PathVariable("id") Integer id) {
        logger.info("Starting of accounts delete request from accounts application");
        if (!(accountServiceImpl.isActive(id)))
            throw new AccountNotFoundException(accNotFound + id);
        return accountServiceImpl.deleteCustomerAndAccount(id);
    }

}
