package com.mavericsystems.customer.service;

import com.mavericsystems.customer.model.Customer;
import com.mavericsystems.customer.model.RequiredResponse;
import com.mavericsystems.customer.dto.UpdateCustomerDTO;
import com.mavericsystems.customer.model.CustomerAllData;

import java.util.List;

public interface CustomerService {
    RequiredResponse addCustomer(RequiredResponse customer);
    CustomerAllData getAllDataByCustomerId(Integer customerId);
    List<Customer> getAllCustomer();
    Boolean customerIsActive(Integer customerId);
    Customer customerPresent(Integer customerId);
    Customer updateCustomer(Integer id, UpdateCustomerDTO customer);
    String deleteCustomer(Integer id);
}
