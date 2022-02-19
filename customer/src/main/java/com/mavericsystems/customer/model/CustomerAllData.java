package com.mavericsystems.customer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAllData {
    List<Account> accountList;
    Customer customer;
}
