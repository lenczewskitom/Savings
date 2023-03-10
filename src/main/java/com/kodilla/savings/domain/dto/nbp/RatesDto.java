package com.kodilla.savings.domain.dto.nbp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RatesDto {

    @JsonProperty("mid")
    private BigDecimal rate;
}
