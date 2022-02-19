package com.mavericsystems.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
     @NotBlank(message = "HouseName is mandatory")
     private String houseName;
     @NotBlank(message = "Place is mandatory")
     private String place;

}
