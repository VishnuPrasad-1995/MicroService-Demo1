package com.mavericsystems.account.repo;

import com.mavericsystems.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account,Integer> {

}
