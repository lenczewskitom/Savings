package com.kodilla.savings.repository;

import com.kodilla.savings.domain.AccountBalance;
import com.kodilla.savings.domain.AccountDeposit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBalanceRepository extends CrudRepository<AccountBalance, Long> {

    @Query()
    AccountBalance getLastAccountBalance();

    @Override
    AccountBalance save(AccountBalance accountBalance);
}
