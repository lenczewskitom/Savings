package com.kodilla.savings.domain;

import com.kodilla.savings.domain.enums.DepositType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.*;
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
@Entity(name = "AccountDeposit")
public class AccountDeposit {

    public AccountDeposit(BigDecimal depositValue, DepositType depositType) {
        this.depositDate = LocalDate.now();
        this.depositValue = depositValue;
        this.depositType = depositType;
    }

    @Id
    @GeneratedValue
    @NotNull
    private long depositId;
    private LocalDate depositDate;
    private BigDecimal depositValue;
    @Enumerated(EnumType.STRING)
    private DepositType depositType;
}
