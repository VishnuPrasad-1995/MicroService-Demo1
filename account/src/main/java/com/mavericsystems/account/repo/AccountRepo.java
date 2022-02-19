package com.mavericsystems.account.repo;

import com.mavericsystems.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AccountRepo extends JpaRepository<Account,Integer> {

    List<Account> findByCustomerId(Integer id);

    Account findByAccountId(Integer id);
}
