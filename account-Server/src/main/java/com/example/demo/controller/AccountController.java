package com.example.demo.controller;

import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/getaccount")
    public List<Account> getAccount()
    {
        return accountService.getAccount();
    }

    @PostMapping("/createaccount")
    public  String   createAccount( @Valid @RequestBody String CusAccnum)
    {
        Account account1 =new Account();

        account1.setCustomerAccNum(CusAccnum);
        return accountService.createAccount(account1).getCustomerAccNum();
    }
}
