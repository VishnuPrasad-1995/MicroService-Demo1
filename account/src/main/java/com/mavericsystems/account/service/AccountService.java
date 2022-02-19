package com.mavericsystems.account.service;
import com.mavericsystems.account.enums.AccountType;
import com.mavericsystems.account.model.Account;
import com.mavericsystems.account.dto.UpdateBalanceDTO;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    List<Account> getAccountDetailsByCustomerId(Integer id);
    List<Account> getAllAccount();
    Account getAccountDetailsByAccountId(Integer id);
    String deleteAccount(Integer id);
    String updateBalance(Integer id, UpdateBalanceDTO updateBalance);

    Boolean deleteCustomerAndAccount(Integer id);
    Boolean isActive(Integer id);

    Boolean isActiveAccount(Integer id);
    Boolean isCustomerSingleAccountHolder(Integer id);

    Boolean isAccountTypeAlreadyExist(Integer customerId, AccountType accountType);
}
