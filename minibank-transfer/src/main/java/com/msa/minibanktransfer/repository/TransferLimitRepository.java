package com.msa.minibanktransfer.repository;

import com.msa.minibanktransfer.domain.TransferLimit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferLimitRepository extends JpaRepository<TransferLimit, Long> {
}
