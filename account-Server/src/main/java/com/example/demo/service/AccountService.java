package com.example.demo.service;

import com.example.demo.model.Account;


import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    List<Account> getAccountDetailsByCustomerId(Integer id);
    List<Account> getAccount();
    Boolean isActiveAccount(Integer id);
    Boolean isActive(Integer id);
    String deleteCustomerAndAccount(Integer id);





}
