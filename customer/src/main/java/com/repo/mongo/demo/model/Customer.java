package com.repo.mongo.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@Document(collection="customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    
   private int id;
    private String customerId;
    String name;
    @NotBlank(message="Account cannot be null")
    private String CustomerAccNum;
    @Min(value = 1,message = "Age should be numbers greater than 1")
    private int age;
    @Size(min = 10,message = "phone number should be 10 digit number")
   private String phoneNum;
    private Date createdDate;
    private Boolean isActive;
    private String lastName;

    public Customer(String customerId, String name, String customerAccNum, int age, String phoneNum, Date createdDate, Boolean isActive, String lastName) {
        this.customerId = customerId;
        this.name = name;
       this.CustomerAccNum = customerAccNum;
        this.age = age;
        this.phoneNum = phoneNum;
        this.createdDate = createdDate;
        this.isActive = isActive;
        this.lastName = lastName;
    }
}
