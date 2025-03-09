package com.msa.minibankinquiry.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document("customer_details")
@Getter
public class CustomerDetail {

    @Id
    @Field(value = "_id", targetType = FieldType.OBJECT_ID)
    private final Long customerId;
    private final String customerName;

    @Getter(AccessLevel.NONE)
    private final Gender gender;
    private final String phoneNumber;
    private final String address;
    private final BigDecimal oneDayTransferLimit;
    private final BigDecimal oneTimeTransferLimit;
    private final List<Account> accounts;

    @PersistenceCreator
    private CustomerDetail(Long customerId, String customerName, Gender gender, String phoneNumber, String address, BigDecimal oneDayTransferLimit, BigDecimal oneTimeTransferLimit, List<Account> accounts) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.oneDayTransferLimit = oneDayTransferLimit;
        this.oneTimeTransferLimit = oneTimeTransferLimit;
        this.accounts = Objects.requireNonNullElseGet(accounts, ArrayList::new);
    }

    @Builder
    private CustomerDetail(Long customerId, String customerName, String gender, String phoneNumber, String address, BigDecimal oneDayTransferLimit, BigDecimal oneTimeTransferLimit, List<Account> accounts) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.gender = Gender.get(gender);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.oneDayTransferLimit = oneDayTransferLimit;
        this.oneTimeTransferLimit = oneTimeTransferLimit;
        this.accounts = accounts;
    }

    public String getGender() {
        return this.gender.getCode();
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

}
