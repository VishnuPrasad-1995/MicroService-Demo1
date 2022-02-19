package com.mavericsystems.customer.model;

import com.mavericsystems.customer.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer accountId;
    private Integer customerId;
    private LocalDate accountCreationDate;
    private AccountType accountType;
    @Min(value = 1, message = "Account Balance should have some value greater than 0")
    private Integer accountBalance;
    private Boolean isActive;
    private LocalDateTime lastAmountReceivedDate;


}
