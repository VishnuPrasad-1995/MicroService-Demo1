package com.mavericsystems.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;
    private Integer customerId;
    private LocalDate accountCreationDate;

    public Account(Integer customerId, LocalDate accountCreationDate) {
        this.customerId = customerId;
        this.accountCreationDate = accountCreationDate;
    }
}
