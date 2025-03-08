package com.msa.minibanktransfer.repository;

import com.msa.minibanktransfer.domain.CustomerSequence;
import com.msa.minibanktransfer.domain.TransferHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransferHistoryRepository extends JpaRepository<TransferHistory, CustomerSequence> {

    @Query("SELECT th.customerSequence.sequence AS seq FROM TransferHistory th WHERE th.customerSequence.customerId=:customerId ORDER BY th.customerSequence.sequence DESC LIMIT 1")
    Long findLastSequenceByCustomerId(Long customerId);

}
