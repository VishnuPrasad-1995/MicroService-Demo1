package com.mavericsystems.customer.controller;

import com.mavericsystems.customer.exception.CustomerAlreadyExistException;
import com.mavericsystems.customer.exception.CustomerNotFoundException;
import com.mavericsystems.customer.model.Customer;
import com.mavericsystems.customer.model.RequiredResponse;
import com.mavericsystems.customer.dto.UpdateCustomerDTO;
import com.mavericsystems.customer.model.CustomerAllData;
import com.mavericsystems.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/CustomerCreation")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    String custNotFound = "Customer Not Found with : " ;
    String custAlreadyExist = "Customer Already exist : " ;


    @PostMapping("/addCustomer")
    public ResponseEntity<RequiredResponse> addCustomer(@Validated @RequestBody RequiredResponse customer){
            Integer customerId = customer.getCustomer().getCustomerId();
            if(customerService.customerPresent(customerId)!=null){
                throw new CustomerAlreadyExistException(custAlreadyExist + customerId);//Checks for customer with ID passed in request body. If Already exist with this Id throws exception
            }
            return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);//Customer and Account value are send to the addCustomer method
    }




   @GetMapping("/customerdetails/{id}")
    public ResponseEntity<CustomerAllData> getAllDataByCustomerId(@PathVariable("id") Integer id){
          if(Boolean.FALSE.equals(customerService.customerIsActive(id)))
          {
              throw new CustomerNotFoundException(custNotFound + id);//this method checks whether a customer exist with this customer id as well as if there is no such customer with such customer id
          }

          return new ResponseEntity<>(customerService.getAllDataByCustomerId(id), HttpStatus.FOUND);



   }


    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return new ResponseEntity<>(customerService.getAllCustomer(),HttpStatus.OK);
    }

    @PutMapping("/updateDetails/{id}")
    public ResponseEntity<Customer> updateCustomerDetails(@PathVariable("id") Integer id,@Valid @RequestBody UpdateCustomerDTO customer){//only specific objects in the DTO object can be updated for a customer
        if(Boolean.FALSE.equals(customerService.customerIsActive(id)))
        {
            throw new CustomerNotFoundException(custNotFound + id);
        }
        return new ResponseEntity<>(customerService.updateCustomer(id,customer), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomerDetails(@PathVariable("id") Integer id){
        if(Boolean.FALSE.equals(customerService.customerIsActive(id)))
        {
            throw new CustomerNotFoundException(custNotFound + id);
        }
        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }






}
