package com.msa.minibankaccount.repository;

import com.msa.minibankaccount.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomerId(Long customerId);

}
