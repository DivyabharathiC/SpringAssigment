package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepo accountRepo;

    @Override
    public List<Account> getAccount() {
        return accountRepo.findAll();

    }

    @Override
    public Account createAccount(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public List<Account> getAccountDetailsByCustomerId(Integer id) {
        return accountRepo.findByCustomerId(id);
    }

    @Override
    public Boolean isActiveAccount(Integer id) {
        Account account = accountRepo.findByAccountId(id);
        if (account == null) {
            return false;
        }
        return account.getIsActive();
    }

    @Override
    public Boolean isActive(Integer id) {
        List<Account> accounts = new ArrayList<>(accountRepo.findByCustomerId(id));
        if (accounts.isEmpty()) {
            return false;
        }
        for (Account account : accounts) {
            if (!(account.getIsActive()))
                return true;
        }
        return false;
    }

    @Override
    public String deleteCustomerAndAccount(Integer id) {
        List<Account> accounts = new ArrayList<>(accountRepo.findByCustomerId(id));
        for (Account account : accounts) {
            account.setIsActive(false);
            accountRepo.save(account);
        }

        return "Account deleted for the customer";
    }

}