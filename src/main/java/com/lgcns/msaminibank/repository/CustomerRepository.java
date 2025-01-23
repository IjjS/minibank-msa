package com.lgcns.msaminibank.repository;

import com.lgcns.msaminibank.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
