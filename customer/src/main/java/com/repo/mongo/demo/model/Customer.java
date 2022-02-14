package com.repo.mongo.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection="books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    String id;


    String name;
    String CustomerAccNum;
    int age;
    String phoneNum;

    public Customer(String name, String CustomerAccNum,int age,String phoneNum) {
        this.name=name;
        this.CustomerAccNum=CustomerAccNum;
        this.age=age;
        this.phoneNum=phoneNum;
    }
}
