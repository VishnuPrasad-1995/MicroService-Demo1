package com.mavericsystems.customer.service;

import com.mavericsystems.customer.model.Customer;

import java.util.List;

public interface CustomerService {
    public Customer addCustomer(Customer customer);
    public Customer getCostumerDetails(Integer customerId);
    public List<Customer> getAllCustomer();

}
