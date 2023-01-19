package com.kodilla.savings.domain.dto.nbp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RatesDto {

    private BigDecimal mid;
}
