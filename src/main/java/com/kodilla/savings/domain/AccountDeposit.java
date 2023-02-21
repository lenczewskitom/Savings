package com.kodilla.savings.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccountDeposit {

    public AccountDeposit(long depositId, BigDecimal depositValue) {
        this.depositId = depositId;
        this.depositDate = LocalDate.now();
        this.depositValue = depositValue;
    }

    @Id
    @GeneratedValue
    @NotNull
    private long depositId;
    private LocalDate depositDate;
    private BigDecimal depositValue;
}
