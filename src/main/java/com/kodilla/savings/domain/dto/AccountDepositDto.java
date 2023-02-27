package com.kodilla.savings.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
