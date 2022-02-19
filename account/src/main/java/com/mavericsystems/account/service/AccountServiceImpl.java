package com.mavericsystems.account.service;


import com.mavericsystems.account.enums.AccountType;
import com.mavericsystems.account.model.Account;
import com.mavericsystems.account.dto.UpdateBalanceDTO;
import com.mavericsystems.account.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepo accountRepo;
    @Override
    public Account createAccount(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public List<Account> getAccountDetailsByCustomerId(Integer id) {
        return accountRepo.findByCustomerId(id);


    }
    public Account getAccountDetailsByAccountId(Integer id) {
        return accountRepo.findByAccountId(id);
    }

    @Override
    public String updateBalance(Integer id, UpdateBalanceDTO updateBalance) {
        Account account = accountRepo.findByAccountId(id);
        account.setAccountBalance(account.getAccountBalance()+updateBalance.getAmountReceived());//balance updated
        account.setLastAmountReceivedDate(updateBalance.getLocalDateTime());//last amount received date is passed
        accountRepo.save(account);
        return "Updated balance is : " + account.getAccountBalance();
    }

    @Override
    public Boolean deleteCustomerAndAccount(Integer id) {
       List<Account> accounts = new ArrayList<>(accountRepo.findByCustomerId(id));
       //sets all the accounts as inactive under this customerId
       for(Account account : accounts){
           account.setIsActive(false);
           accountRepo.save(account);
        }

       return false;
    }
    @Override
    public String deleteAccount(Integer id) {
        Account account = accountRepo.findByAccountId(id);
        //sets account as inactive with this accId
        account.setIsActive(false);
        accountRepo.save(account);
        return "This Account id is deleted";
    }

    @Override
    public Boolean isActive(Integer id) {
        List<Account> accounts = new ArrayList<>(accountRepo.findByCustomerId(id));
        //below if checks whether the id passed is valid
        if(accounts.isEmpty()){
            return false;
        }
        for(Account account : accounts){
            //below if checks whether any account is active for this customer
            if(Boolean.TRUE.equals(account.getIsActive()))
                return true;
        }
        return false;
    }




    @Override
    public Boolean isActiveAccount(Integer id) {
        Account account = accountRepo.findByAccountId(id);
        //below if checks whether the id passed is valid
        if (account==null) {
            return false;
        }
        return account.getIsActive();
    }

    @Override //this checks whether customer has only one type of account
    public Boolean isCustomerSingleAccountHolder(Integer id) {
        Integer customerId = accountRepo.findByAccountId(id).getCustomerId();
        List<Account> accounts = new ArrayList<>(accountRepo.findByCustomerId(customerId));
        int count = 0;
        for(Account account : accounts){
            if(Boolean.TRUE.equals(account.getIsActive()))
                count++;

            if(count>1)
                return false;
        }
        return true;
    }

    @Override //this checks whether account type already exists for this customer
    public Boolean isAccountTypeAlreadyExist(Integer customerId, AccountType accountType) {

        List<Account> accountTypeList = new ArrayList<>(accountRepo.findByCustomerId(customerId));
        for(Account account : accountTypeList){
            if(account.getAccountType()==accountType)
                return true;
        }
        return false;


    }


    @Override
    public List<Account> getAllAccount() {
        return accountRepo.findAll();
    }






}
