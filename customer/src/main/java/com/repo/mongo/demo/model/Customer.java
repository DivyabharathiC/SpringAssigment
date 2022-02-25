package com.repo.mongo.demo.model;


import com.repo.mongo.demo.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Document(collection = "customerTable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private String id;
    @Min(value = 1, message = "customer id should be numbers greater than 1")
    private Integer customerId;
    private LocalDate customerCreationDate;
    @NotBlank(message = "Name is mandatory")
    private String customerFirstName;
    private String customerLastName;
    @Size(min = 10, message = "phone number should be a 10 digit number")
    private String phoneNumber;
    private Boolean isCustomerActive;
    private LocalDateTime customerInfoEditedDate;
    private CustomerType customerType;


    public Customer(Integer customerId, LocalDate customerCreationDate, String customerFirstName, String customerLastName, String phoneNumber, Boolean isCustomerActive, LocalDateTime customerInfoEditedDate, CustomerType customerType) {
        this.customerId = customerId;
        this.customerCreationDate = customerCreationDate;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.phoneNumber = phoneNumber;
        this.isCustomerActive = isCustomerActive;
        this.customerInfoEditedDate = customerInfoEditedDate;
        this.customerType = customerType;

    }
}
