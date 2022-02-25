package com.repo.mongo.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAllData {
    List<Account> accountList;
    Customer customer;

    public CustomerAllData(List<Account> accountList) {
        this.accountList = accountList;
    }

    public CustomerAllData(Customer customer) {
        this.customer = customer;
    }
}
