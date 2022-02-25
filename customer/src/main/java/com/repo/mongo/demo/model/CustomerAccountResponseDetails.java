package com.repo.mongo.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerAccountResponseDetails {

    @Valid
    @NotNull(groups = Account.class, message = "Account Should not be null")
    Account account;
    @Valid
    @NotNull(groups = Customer.class, message = "Account Should not be null")
    Customer customer;
}
