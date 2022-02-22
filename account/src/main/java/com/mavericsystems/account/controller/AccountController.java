package com.mavericsystems.account.controller;

import com.mavericsystems.account.dto.AccountDetailsDTO;
import com.mavericsystems.account.dto.AddNewTypeOfAccountForExistingCustomerDTO;
import com.mavericsystems.account.exceptions.AccountCannotBeDeletedException;
import com.mavericsystems.account.exceptions.AccountNotFoundException;
import com.mavericsystems.account.exceptions.AccountTypeAlreadyExist;
import com.mavericsystems.account.model.Account;
import com.mavericsystems.account.dto.UpdateBalanceDTO;
import com.mavericsystems.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.mavericsystems.account.constants.Constant.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/account") //this method is only called from customer application
    public Account addAccount(@Valid @RequestBody AccountDetailsDTO account){
        Account account1 = new Account(account.getCustomerId(),account.getAccountCreationDate(),account.getAccountType(),account.getAccountBalance(),account.getIsActive(), LocalDateTime.now());
        return accountService.createAccount(account1);
    }

    @PostMapping("/account/new")// this method is called from account application as it works only for existing customer
    public ResponseEntity<Account> addNewTypeOfAcctForExistingCustomer(@Valid @RequestBody AddNewTypeOfAccountForExistingCustomerDTO account){
       //checks whether customer present with this customer id
        if(Boolean.FALSE.equals(accountService.isActive(account.getCustomerId())))
            throw new AccountNotFoundException(noExistingAccountFound);
        //checks whether this account type already exist for this customer
        if(Boolean.TRUE.equals(accountService.isAccountTypeAlreadyExist(account.getCustomerId(),account.getAccountType())))
            throw new AccountTypeAlreadyExist(accTypeAlreadyExist);
        Account account1 = new Account(account.getCustomerId(), LocalDate.now(),account.getAccountType(),account.getAccountBalance(),true,LocalDateTime.now());
        return new ResponseEntity<>(accountService.createAccount(account1), HttpStatus.CREATED);
    }

    @GetMapping("/account/{id}")//this method is accessed only from customer application
    public List<Account> getAccountDetailsByCustomerId(@PathVariable("id") Integer id){
        //this method checks whether the any account is active for this customer as well as entered customer id is valid
        if(Boolean.FALSE.equals(accountService.isActive(id)))
            throw new AccountNotFoundException(accNotFound + id);
        return accountService.getAccountDetailsByCustomerId(id);
    }

        @GetMapping("/accountdetails/{id}")//this method is accessed from account application
    public ResponseEntity<Account> getAccountDetailsByAccountId(@PathVariable("id") Integer id){
        // checks only account is active or not and entered accountId is valid
        if(Boolean.FALSE.equals(accountService.isActiveAccount(id)))
            throw new AccountNotFoundException(accNotFound + id);
        return new ResponseEntity<>(accountService.getAccountDetailsByAccountId(id),HttpStatus.FOUND);
    }

    @PutMapping("/addMoney/{id}")//this method is accessed only from account application
    public ResponseEntity<String> addMoney(@PathVariable("id") Integer id,@Valid @RequestBody UpdateBalanceDTO updateBalance){
        if(Boolean.FALSE.equals(accountService.isActiveAccount(id)))
            throw new AccountNotFoundException(accNotFound + id);
        updateBalance.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity<>(accountService.updateBalance(id,updateBalance),HttpStatus.OK);
    }

    @GetMapping("/allaccounts")
    public List<Account> getAllAccount(){
        return accountService.getAllAccount();
    }

    @DeleteMapping("/customer/account/{id}")// this method is called from customer application
    public Boolean deleteCustomerAndAccount(@PathVariable("id") Integer id){
        if(Boolean.FALSE.equals(accountService.isActive(id)))
            throw new AccountNotFoundException(accNotFound + id);
        return accountService.deleteCustomerAndAccount(id);
    }

    @DeleteMapping("/account/{id}")//this is called from account application and only works for valid customers with more than one account
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Integer id){
        if(Boolean.FALSE.equals(accountService.isActiveAccount(id)))
            throw new AccountNotFoundException(accNotFound + id);
        if(Boolean.TRUE.equals(accountService.isCustomerSingleAccountHolder(id)))
            throw new AccountCannotBeDeletedException(accCannotBeDeleted);
        return new ResponseEntity<>(accountService.deleteAccount(id),HttpStatus.OK);

    }
}
