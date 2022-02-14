package com.mavericsystems.customer.service;


import com.mavericsystems.customer.model.Address;
import com.mavericsystems.customer.model.Customer;
import com.mavericsystems.customer.repo.AddressRepo;
import com.mavericsystems.customer.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    AddressRepo addressRepo;

    @Override
    public Customer addCustomer(Customer customer) {
        Integer customerId = customer.getCustomerId();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Integer> httpEntity = new HttpEntity<>(customerId,header);
        Integer accId = restTemplate.postForObject("http://account/AccountCreation/addAccount",httpEntity,Integer.class);
        customer.setAccId(accId);
        customer.setIsAccountCreated((accId!=null));
       return customerRepo.save(new Customer(LocalDate.now(),customer.getCustomerName(),customer.getCustomerId(),customer.getPhoneNumber(),customer.getIsAccountCreated(),customer.getAccId(),addressRepo.save(new Address(customer.getCustomerName(),customer.getAddress().getHouseName(),customer.getAddress().getPlace()))));

    }




    @Override
    public Customer getCostumerDetails(Integer customerId) {
        Optional<Customer> customerData = customerRepo.findByCustomerId(customerId);
        if(customerData.isPresent()) {
            return customerData.get();
        }
        else return null;

    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }


}
