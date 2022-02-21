package com.repo.mongo.demo.model;

import com.repo.mongo.demo.enums.AccountType;
import org.springframework.data.annotation.Id;


import javax.validation.constraints.Size;
import java.util.Date;

public class Account {
    @Id

    private Integer accountId;

    private int customerId;

    private String CustomerAccNum;
    private Date createdDate;
    @Size(min = 0, max = 50000)
    private String accountBalance;
    private AccountType accountType;
    private Boolean isActive;

}
