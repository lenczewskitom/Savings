package com.kodilla.savings.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AccountDepositDto {

    private long depositId;
    private LocalDate depositDate;
    private BigDecimal depositValue;
}
