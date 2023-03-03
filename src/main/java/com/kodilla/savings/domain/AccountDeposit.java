package com.kodilla.savings.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;


@NamedNativeQuery(
        name = "AccountDeposit.reverseDepositsList",
        query = "SELECT * FROM ACCOUNT_DEPOSIT ORDER BY deposit_Id DESC",
        resultClass = AccountDeposit.class
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AccountDeposit {

    public AccountDeposit(BigDecimal depositValue) {
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
