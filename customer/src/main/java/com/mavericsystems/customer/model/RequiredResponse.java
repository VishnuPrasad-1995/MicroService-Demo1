package com.mavericsystems.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@Data
public class RequiredResponse {
    @Valid
    @NotNull(groups = Account.class,  message = "Account Should not be null" )
    Account account;
    @Valid
    @NotNull(groups = Customer.class,  message = "Account Should not be null")
    Customer customer;

}
