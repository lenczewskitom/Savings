package com.kodilla.savings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccountDepositDto {

    private long accountDepositId;
    private LocalDate accountDepositDate;
    private BigDecimal accoountDepositValue;
}
