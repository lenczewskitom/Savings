package com.kodilla.savings.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AccountBalanceDto {

    private long accountBalanceId;
    private BigDecimal balance;
}
