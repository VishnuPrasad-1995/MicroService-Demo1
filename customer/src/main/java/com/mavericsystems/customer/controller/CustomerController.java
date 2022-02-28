package com.mavericsystems.customer.controller;

import com.mavericsystems.customer.exception.CustomerAlreadyExistException;
import com.mavericsystems.customer.exception.CustomerNotFoundException;
import com.mavericsystems.customer.model.Customer;
import com.mavericsystems.customer.model.RequiredResponse;
import com.mavericsystems.customer.dto.UpdateCustomerDTO;
import com.mavericsystems.customer.model.CustomerAllData;
import com.mavericsystems.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

import static com.mavericsystems.customer.constants.Constant.custAlreadyExist;
import static com.mavericsystems.customer.constants.Constant.custNotFound;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);


    @PostMapping("/customer")
    public ResponseEntity<RequiredResponse> addCustomer(@Validated @RequestBody RequiredResponse customer){
            Integer customerId = customer.getCustomer().getCustomerId();
            if(customerService.customerPresent(customerId)!=null){
                throw new CustomerAlreadyExistException(custAlreadyExist + customerId);//Checks for customer with ID passed in request body. If Already exist with this Id throws exception
            }
            logger.info("Controller class");
            return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);//Customer and Account value are send to the addCustomer method
    }




   @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerAllData> getAllDataByCustomerId(@PathVariable("id") Integer id){
          if(Boolean.FALSE.equals(customerService.customerIsActive(id)))
          {
              throw new CustomerNotFoundException(custNotFound + id);//this method checks whether a customer exist with this customer id as well as if there is no such customer with such customer id
          }

          return new ResponseEntity<>(customerService.getAllDataByCustomerId(id), HttpStatus.FOUND);



   }


    @GetMapping("/customer/allCustomer")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return new ResponseEntity<>(customerService.getAllCustomer(),HttpStatus.OK);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomerDetails(@PathVariable("id") Integer id,@Valid @RequestBody UpdateCustomerDTO customer){//only specific objects in the DTO object can be updated for a customer
        if(Boolean.FALSE.equals(customerService.customerIsActive(id)))
        {
            throw new CustomerNotFoundException(custNotFound + id);
        }
        return new ResponseEntity<>(customerService.updateCustomer(id,customer), HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteCustomerDetails(@PathVariable("id") Integer id){
        if(Boolean.FALSE.equals(customerService.customerIsActive(id)))
        {
            throw new CustomerNotFoundException(custNotFound + id);
        }
        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }






}
