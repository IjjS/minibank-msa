package com.lgcns.msaminibank.dto;

import com.lgcns.msaminibank.domain.Customer;

public record CustomerDto(Integer cstmId, String cstmNm, String cstmAge, String cstmGnd, String cstmPn, String cstmAdr) {

    public static CustomerDto from(Customer customer) {
        return new CustomerDto(customer.getCstmId(), customer.getCstmNm(), customer.getCstmAge(), customer.getCstmGnd(), customer.getCstmPn(), customer.getCstmAdr());
    }
}
