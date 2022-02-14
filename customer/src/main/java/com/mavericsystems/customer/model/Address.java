package com.mavericsystems.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Address")
public class Address {
     @Id
     String id;
     String customerName;
     @NotBlank(message = "HouseName is mandatory")
     String houseName;
     String place;

     public Address(String customerName, String houseName, String place) {
          this.customerName = customerName;
          this.houseName = houseName;
          this.place = place;
     }
}
