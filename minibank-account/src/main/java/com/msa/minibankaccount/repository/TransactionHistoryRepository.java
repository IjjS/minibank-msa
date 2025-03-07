package com.msa.minibankaccount.repository;

import com.msa.minibankaccount.domain.AccountSequence;
import com.msa.minibankaccount.domain.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, AccountSequence> {

    @Query("SELECT th.accountSequence.sequence AS seq FROM TransactionHistory th WHERE th.accountSequence.accountNumber=:accountNumber ORDER BY th.accountSequence.sequence DESC LIMIT 1")
    Long findLastSequenceByAccountNumber(Long accountNumber);

}
