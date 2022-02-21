package com.mavericsystems.customer.feign;

import com.mavericsystems.customer.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "account", fallbackFactory = HystrixFallBackFactory.class)
public interface AccountFeign {
    @GetMapping(value = "/accounts/account/{id}")
    List<Account> getAccountDetailsByCustomerId(@PathVariable("id") Integer id);
    @DeleteMapping(value = "/accounts/customer/account/{id}")
    Boolean deleteCustomerAndAccount(@PathVariable("id") Integer id);
}
