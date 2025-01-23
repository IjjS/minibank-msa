package com.lgcns.msaminibank.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_cstm")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Customer {

    @Id
    private Integer cstmId;
    private String cstmNm;
    private String cstmAge;
    private String cstmGnd;
    private String cstmPn;
    private String cstmAdr;

}
