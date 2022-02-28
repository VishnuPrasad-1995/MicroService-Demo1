package com.mavericsystems.customer.feign;

import com.mavericsystems.customer.config.CustomerRetryClientConfig;
import com.mavericsystems.customer.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@FeignClient(name = "account", configuration = CustomerRetryClientConfig.class,fallbackFactory = HystrixFallBackFactory.class)
//@FeignClient(name = "account", fallbackFactory = HystrixFallBackFactory.class)
public interface AccountFeign {
    @GetMapping(value = "/accounts/account/{id}")
    List<Account> getAccountDetailsByCustomerId(@PathVariable("id") Integer id);
    @DeleteMapping(value = "/accounts/customer/account/{id}")
    Boolean deleteCustomerAndAccount(@PathVariable("id") Integer id);
    @PostMapping("/accounts/account") //this method is only called from customer application
    public Account addAccount(@Valid @RequestBody Account account);
}
