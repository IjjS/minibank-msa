package com.msa.minibankcustomer.repository;

import com.msa.minibankcustomer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
