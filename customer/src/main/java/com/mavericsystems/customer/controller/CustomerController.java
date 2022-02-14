package com.mavericsystems.customer.controller;

import com.mavericsystems.customer.model.Customer;
import com.mavericsystems.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/CustomerCreation")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer){
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
    }



   @GetMapping("/customerdetails/{id}")
    public Customer getCustomerDetails(@PathVariable("id") Integer id){
        return customerService.getCostumerDetails(id);
   }

    @GetMapping("/getAllCustomer")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }



}
