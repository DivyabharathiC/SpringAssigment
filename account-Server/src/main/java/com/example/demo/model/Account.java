package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="accountholderdata")
@AllArgsConstructor
//@NoArgsConstructor
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String accountHolderName;
    private String accountType;

//    String Nominee;
//    String Branch;
//    int AccountBalance;
//    String IFSC;


    public Account(String accountHolderName, String accountType) {
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
    }

    public Account()
{

}
}
