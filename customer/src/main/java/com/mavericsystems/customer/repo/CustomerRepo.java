package com.mavericsystems.customer.repo;

import com.mavericsystems.customer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepo extends MongoRepository<Customer,String> {
    Customer findByCustomerId(Integer customerId);

}
