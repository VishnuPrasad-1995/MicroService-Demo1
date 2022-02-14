package com.mavericsystems.account.service;

import com.mavericsystems.account.model.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    Account getAccountDetails(Integer id);
    List<Account> getAllAccount();

}
