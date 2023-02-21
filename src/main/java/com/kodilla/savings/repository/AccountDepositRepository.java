package com.kodilla.savings.repository;

import com.kodilla.savings.domain.AccountDeposit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDepositRepository extends CrudRepository<AccountDeposit, Long> {
}
