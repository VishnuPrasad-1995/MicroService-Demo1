package com.mavericsystems.customer.service;


import com.mavericsystems.customer.dto.UpdateCustomerDTO;
import com.mavericsystems.customer.exception.CustomFeignException;
import com.mavericsystems.customer.feign.AccountFeign;
import com.mavericsystems.customer.model.*;
import com.mavericsystems.customer.repo.CustomerRepo;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

import static com.mavericsystems.customer.constants.Constant.serverDown;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    AccountFeign accountFeign;

    @Override
    public RequiredResponse addCustomer(RequiredResponse customer) {

       try{
        Account account = new Account();
        account.setCustomerId(customer.getCustomer().getCustomerId());
        account.setAccountCreationDate(LocalDate.now());
        account.setAccountType(customer.getAccount().getAccountType());
        account.setAccountBalance(customer.getAccount().getAccountBalance());
        account.setIsActive(true);
        customer.setAccount(account);
        //call to account application for creating account for this customer happens here using restTemplate
        customer.setAccount(accountFeign.addAccount(customer.getAccount()));
        customer.setCustomer(customerRepo.save(new Customer(LocalDate.now(),customer.getCustomer().getCustomerFirstName(),customer.getCustomer().getCustomerLastName(),customer.getCustomer().getCustomerId(),customer.getCustomer().getPhoneNumber(),customer.getAccount().getIsActive(),customer.getCustomer().getAddress(),customer.getCustomer().getCustomerType(), LocalDateTime.now())));
        return customer;
    }
         catch(Exception e) {
            throw new CustomFeignException(serverDown);
        }
    }




    @Override
    public CustomerAllData getAllDataByCustomerId(Integer customerId) {
        //this gets all data from account application for a customer

        Customer customerData = customerRepo.findByCustomerId(customerId);
        //get call to account app using feign client
       try{
            List<Account> accounts = accountFeign.getAccountDetailsByCustomerId(customerId);
        return new CustomerAllData(accounts,customerData);
        }
        catch(HystrixRuntimeException e) {
            throw new CustomFeignException(serverDown);
       }

    }


    @Override
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    @Override
    public Boolean customerIsActive(Integer customerId) {
       Customer customer = customerRepo.findByCustomerId(customerId);
       //the below if condition checks whether a customer exist with that customer ID
        if (customer==null) {
            return false;
        }
        return customer.getIsActive();

    }
    @Override
    public Customer customerPresent(Integer customerId) {
        return customerRepo.findByCustomerId(customerId);

    }

    @Override
    public Customer updateCustomer(Integer id, UpdateCustomerDTO customer) {
        //create a customer object stores the current value of the customer
        Customer customerUpdate = this.customerRepo.findByCustomerId(id);
        //replace the value with the values to be updated
        customerUpdate.setAddress(customer.getAddress());
        customerUpdate.setPhoneNumber(customer.getPhoneNumber());
        customerUpdate.setCustomerLastName(customer.getCustomerLastName());
        customerUpdate.setCustomerInfoEditedDate(LocalDateTime.now());
        //save it back to repo with changes
        return customerRepo.save(customerUpdate);
    }

    @Override
    public String deleteCustomer(Integer id) {

        //when deleting the customer in customer application , account against the customer also gets deleted
        //feign call to account application
        //only soft deletion is used
     try {
            Customer customer = customerRepo.findByCustomerId(id);
            customer.setIsActive(accountFeign.deleteCustomerAndAccount(id));
            customerRepo.save(customer);
            return "Customer deleted for id : "+id;
      }
        catch(HystrixRuntimeException e) {
          throw new CustomFeignException(serverDown);
      }


    }


}
