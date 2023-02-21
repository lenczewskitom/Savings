package com.kodilla.savings.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountBalance {

    @Id
    @GeneratedValue
    @NotNull
    private long accountBalanceId;
    private BigDecimal balance= new BigDecimal(0);

    public void updateBalance(BigDecimal value) {
        balance = balance.add(value);
    }
}
