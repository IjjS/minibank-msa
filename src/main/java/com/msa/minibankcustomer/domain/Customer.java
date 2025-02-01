package com.msa.minibankcustomer.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Customer {

    @Id
    private Long id;
    private String name;
    private String age;
    private String gender;
    private String phoneNumber;
    private String address;

    public void update(Long customerId, String name, String age, String gender, String phoneNumber, String address) {
        this.id = customerId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

}
