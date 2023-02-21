package com.kodilla.savings.repository;

import com.kodilla.savings.domain.AccountBalance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBalanceRepository extends CrudRepository<AccountBalance, Long> {
}
