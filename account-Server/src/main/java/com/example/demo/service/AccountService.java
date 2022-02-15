package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepo accountRepo;
    public List<Account> getAccount()
    {
    return (List<Account>) accountRepo.findAll();

    }

    public Account createAccount(Account account)
    {
       return accountRepo.save(account);
    }
}
