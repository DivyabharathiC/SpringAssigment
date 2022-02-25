package com.repo.mongo.demo.model;

import com.repo.mongo.demo.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Integer accountId;
    private Integer customerId;
    private String accountBalance;
    private AccountType accountType;
    private Boolean isActive;

}
