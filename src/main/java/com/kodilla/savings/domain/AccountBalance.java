package com.kodilla.savings.domain;

import lombok.Data;


import java.math.BigDecimal;

@Data
public class AccountBalance {

    private BigDecimal balance= new BigDecimal(0);

    public void updateBalance(BigDecimal value) {
        balance = balance.add(value);
    }
}
