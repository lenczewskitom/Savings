package com.kodilla.savings.domain.dto.nbp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NbpResponseDto {

    private List<RatesDto> rates;
}
