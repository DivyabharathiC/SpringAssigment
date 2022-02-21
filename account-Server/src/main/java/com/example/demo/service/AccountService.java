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

    public List<Account> getAccount() {
        return (List<Account>) accountRepo.findAll();

    }

    public Account createAccount(Account account) {
        return accountRepo.save(account);
    }

    public Account findById(Integer id) {
        return accountRepo.findById(id).get();
    }

    public List<Account> getAccountDetailsByCustomerId(Integer id) {
        return accountRepo.findByCustomerId(id);
    }

    public String deleteAccount(Integer id) {
        Account account = accountRepo.findByAccountId(id);
        account.setIsActive(false);
        accountRepo.save(account);
        return "This Account id deleted";
    }
    public Boolean isAccountActive(Integer id) {
        Account account = accountRepo.findByAccountId(id);
        boolean isPresent = false;
        if (account != null) {
            isPresent = true;
        }
        return isPresent;
    }

}