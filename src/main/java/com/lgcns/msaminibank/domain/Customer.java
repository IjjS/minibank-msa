package com.lgcns.msaminibank.domain;

import com.lgcns.msaminibank.dto.CustomerDto;
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

    public void update(Integer cstmId, String cstmNm, String cstmAge, String cstmGnd, String cstmPn, String cstmAdr) {
        this.cstmId = cstmId;
        this.cstmNm = cstmNm;
        this.cstmAge = cstmAge;
        this.cstmGnd = cstmGnd;
        this.cstmPn = cstmPn;
        this.cstmAdr = cstmAdr;
    }

}
