package com.kodilla.savings.domain.coinapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CoinApiResponseDto {

    private BigDecimal rate;
}
