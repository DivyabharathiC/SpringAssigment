package com.repo.mongo.demo.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@NoArgsConstructor
@Data
public class UpdateCusDetails {

    @Size(min = 10,message = "phone number should be a 10 digit number")
    private String phoneNumber;

    @NotBlank(message = "Last Name needs to be updated")
    private String lastName;

    public UpdateCusDetails(String phoneNumber, String lastName) {
        this.phoneNumber = phoneNumber;
        this.lastName = lastName;
    }
}
