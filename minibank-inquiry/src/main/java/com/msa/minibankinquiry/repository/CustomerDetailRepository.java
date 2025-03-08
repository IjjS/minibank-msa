package com.msa.minibankinquiry.repository;

import com.msa.minibankinquiry.domain.CustomerDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerDetailRepository extends MongoRepository<CustomerDetail, Long> {
}
