package com.mavericsystems.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Document(collection = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private String id;
    private LocalDate customerCreationDate;
    @NotBlank(message = "Name is mandatory")
    private String customerName;
    private Integer customerId;
    private Integer phoneNumber;
    private Boolean isAccountCreated;
    private Integer accId;
    private Address address;

    public Customer(LocalDate customerCreationDate, String customerName, Integer customerId, Integer phoneNumber, Boolean isAccountCreated, Integer accId, Address address) {
        this.customerCreationDate = customerCreationDate;
        this.customerName = customerName;
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.isAccountCreated = isAccountCreated;
        this.accId = accId;
        this.address = address;
    }
}
