package com.mavericsystems.customer.dto;

import com.mavericsystems.customer.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCustomerDTO {
    @Size(min = 10,message = "phone number should be a 10 digit number")
    private String phoneNumber;
    @Valid
    @NotNull(groups = Address.class,message = "Address should not be null")
    private Address address;
    @NotBlank(message = "Last Name needs to be updated")
    private String customerLastName;

}
