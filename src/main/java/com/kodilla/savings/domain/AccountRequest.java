package com.kodilla.savings.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AccountRequest {

    public AccountRequest(long depositId, BigDecimal depositValue) {
        this.depositId = depositId;
        this.depositDate = LocalDate.now();
        this.depositValue = depositValue;
    }

    private long depositId;
    private LocalDate depositDate;
    private BigDecimal depositValue;
}
