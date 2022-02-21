package com.example.demo.controller;

import com.example.demo.exceptions.AccountNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.DateTimeAtCreation;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
    public  Account   createAccount( @Valid @RequestBody Account CusAccDetails)
    {
        Account account1 =new Account();

        account1.setCustomerAccNum(CusAccDetails.getCustomerAccNum());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        account1.setCreatedDate(date);
        account1.setAccountBalance(CusAccDetails.getAccountBalance());
        return accountService.createAccount(account1);
       // return accountService.createAccount(account1).getCustomerAccNum();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Account> getById(@PathVariable("id") Integer id){
        Account one= accountService.findById(id);
        return  new ResponseEntity<Account>(one, HttpStatus.OK);
    }
    @GetMapping("/account/{id}")
    public Account getAccountDetailsByCustomerId(@PathVariable("id") Integer id) {
        return (Account) accountService.getAccountDetailsByCustomerId(id);
    }

    @DeleteMapping("/deleteMappedAccount/{id}")
    public boolean deleteMappedAccount(@PathVariable("id") Integer id){
        Account account = getAccountDetailsByCustomerId(id);
        if(!accountService.isAccountActive(account.getAccountId())) {
            throw new AccountNotFoundException("Account is not present in DB" + id);
        }
        accountService.deleteAccount(id);
        return false ;
    }

}
