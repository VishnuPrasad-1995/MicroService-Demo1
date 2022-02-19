package com.mavericsystems.customer.model;

import com.mavericsystems.customer.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private String id;
    private LocalDate customerCreationDate;
    @NotBlank(message = "Name is mandatory")
    private String customerFirstName;
    private String customerLastName;
    @Min(value = 1,message = "customer id should be numbers greater than 1")
    private Integer customerId;
    @Size(min = 10,message = "phone number should be a 10 digit number")
    private String phoneNumber;
    private Boolean isActive;
    @Valid
    @NotNull(groups = Address.class,message = "Address should not be null")
    private Address address;
    private CustomerType customerType;
    private LocalDateTime customerInfoEditedDate;

    public Customer(LocalDate customerCreationDate, String customerFirstName, String customerLastName, Integer customerId, String phoneNumber, Boolean isActive, Address address, CustomerType customerType, LocalDateTime customerInfoEditedDate) {
        this.customerCreationDate = customerCreationDate;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerId = customerId;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.address = address;
        this.customerType = customerType;
        this.customerInfoEditedDate = customerInfoEditedDate;
    }
}
