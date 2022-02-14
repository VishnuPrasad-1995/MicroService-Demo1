package com.mavericsystems.account.controller;

import com.mavericsystems.account.model.Account;
import com.mavericsystems.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/AccountCreation")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/addAccount")
    public Integer addAccount(@Valid @RequestBody Integer customerId){
        Account account = new Account();
        account.setCustomerId(customerId);
        account.setAccountCreationDate(LocalDate.now());
        return accountService.createAccount(account).getAccountId();

    }

    @GetMapping("/accountdetails/{id}")
    public Account getAccountDetails(@PathVariable("id") Integer id){
        return accountService.getAccountDetails(id);
    }

    @GetMapping("/getAllAccounts")
    public List<Account> getAllAccount(){
        return accountService.getAllAccount();
    }

}
