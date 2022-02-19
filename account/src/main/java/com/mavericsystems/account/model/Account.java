package com.mavericsystems.account.model;

import com.mavericsystems.account.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;
    @Min(value = 1, message = "should be a valid customer id")
    private Integer customerId;
    private LocalDate accountCreationDate;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Min(value = 1, message = "Enter some valid account balance amount")
    private Integer accountBalance;
    private Boolean isActive;
    private LocalDateTime lastAmountReceivedDate;

    public Account(Integer customerId, LocalDate accountCreationDate, AccountType accountType, Integer accountBalance, Boolean isActive, LocalDateTime lastAmountReceivedDate) {
        this.customerId = customerId;
        this.accountCreationDate = accountCreationDate;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.isActive = isActive;
        this.lastAmountReceivedDate = lastAmountReceivedDate;
    }
}
