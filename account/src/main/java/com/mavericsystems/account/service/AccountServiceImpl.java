package com.mavericsystems.account.service;

import com.mavericsystems.account.model.Account;
import com.mavericsystems.account.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepo accountRepo;
    @Override
    public Account createAccount(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public Account getAccountDetails(Integer id) {
        Optional<Account> customerData = accountRepo.findById(id);
        if(customerData.isPresent()) {
            return customerData.get();
        }
        else return null;


    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepo.findAll();
    }

}
