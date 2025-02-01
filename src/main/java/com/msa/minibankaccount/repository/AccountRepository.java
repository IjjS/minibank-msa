package com.msa.minibankaccount.repository;

import com.msa.minibankaccount.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
