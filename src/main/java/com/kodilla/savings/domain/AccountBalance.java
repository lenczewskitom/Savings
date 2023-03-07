package com.kodilla.savings.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@NamedQuery(
        name = "AccountBalance.getLastAccountBalance",
        query = "FROM AccountBalance WHERE accountBalanceId = (SELECT MAX(accountBalanceId) FROM AccountBalance)"
)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "AccountBalance")
public class AccountBalance {

    public AccountBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Id
    @GeneratedValue
    @NotNull
    private long accountBalanceId;
    private BigDecimal balance;
}
