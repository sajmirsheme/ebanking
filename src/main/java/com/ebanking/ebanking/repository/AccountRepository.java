package com.ebanking.ebanking.repository;

import com.ebanking.ebanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

   List<Account> findAllByCustomerUsername(String customerUsername);
  Optional<Account> findByAccountNumberAndCustomerUsername(String accountNumber,String customerUsername);

  Optional<Account> findByaccountNumber(String accountNumber);

}
