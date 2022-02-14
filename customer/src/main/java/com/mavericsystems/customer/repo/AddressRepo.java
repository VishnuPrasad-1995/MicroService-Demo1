package com.mavericsystems.customer.repo;

import com.mavericsystems.customer.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepo extends MongoRepository<Address,String> {

}
