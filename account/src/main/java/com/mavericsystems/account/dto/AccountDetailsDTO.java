package com.mavericsystems.account.dto;

import com.mavericsystems.account.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsDTO {
    @Min(value = 1, message = "should be a valid customer id")
    private Integer customerId;
    private LocalDate accountCreationDate;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Min(value = 1, message = "Enter some valid account balance amount")
    private Integer accountBalance;
    private Boolean isActive;
}
