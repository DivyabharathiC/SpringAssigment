package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="accountholderdata")
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message="Account cannot be null")
    String CustomerAccNum;

//    String Nominee;
//    String Branch;
//    int AccountBalance;
//    String IFSC;


    public Account(int id, String customerAccNum) {
        this.id = id;
        this.CustomerAccNum = customerAccNum;
    }

    public Account()
{

}
}
