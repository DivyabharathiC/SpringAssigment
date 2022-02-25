package com.example.demo.model;

import com.example.demo.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "accountData")
@AllArgsConstructor
@NoArgsConstructor

@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;
    private Integer customerId;
    private String customerAccNum;
    private LocalDate accountCreatedDate;
    private String accountBalance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private Boolean isActive;

    public Account(int customerId, String customerAccNum, String accountBalance, AccountType accountType, Boolean isActive) {
        this.customerId = customerId;
        this.customerAccNum = customerAccNum;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
        this.isActive = isActive;
    }


    public Account(Account byAccountId) {
    }
}

