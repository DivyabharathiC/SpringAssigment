package com.example.demo.model;

import com.example.demo.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="accountholderdata")
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class Account {

    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    private int customerId;
    @NotBlank(message="Account cannot be null")
    private String CustomerAccNum;
    private Date createdDate;
    @Size(min=0, max=50000)
    private String accountBalance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private Boolean isActive;

    public Account(int customerId, String customerAccNum, Date createdDate, String accountBalance, AccountType accountType, Boolean isActive) {
        this.customerId = customerId;
        CustomerAccNum = customerAccNum;
        this.createdDate = createdDate;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
        this.isActive = isActive;
    }

    public Account()
{

}
}
