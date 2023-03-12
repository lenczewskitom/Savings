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


@NamedQuery(
        name = "AccountDeposit.reverseDepositsList",
        query = "from AccountDeposit ORDER BY depositId DESC"
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long depositId;
    private LocalDate depositDate;
    private BigDecimal depositValue;
    @Enumerated(EnumType.STRING)
    private DepositType depositType;
}
