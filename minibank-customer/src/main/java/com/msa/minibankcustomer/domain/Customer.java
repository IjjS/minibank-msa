package com.msa.minibankcustomer.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;

    @Getter(AccessLevel.NONE)
    private Gender gender;
    private String phoneNumber;
    private String address;

    @Builder
    private Customer(String name, Integer age, String gender, String phoneNumber, String address) {
        this.name = name;
        this.age = age;
        this.gender = Gender.get(gender);
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getGender() {
        return this.gender.getCode();
    }

    public void update(Long customerId, String name, Integer age, String gender, String phoneNumber, String address) {
        this.id = customerId;
        this.name = name;
        this.age = age;
        this.gender = Gender.get(gender);
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

}
