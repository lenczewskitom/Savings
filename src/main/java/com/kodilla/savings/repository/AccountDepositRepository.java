package com.kodilla.savings.repository;

import com.kodilla.savings.domain.AccountDeposit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDepositRepository extends CrudRepository<AccountDeposit, Long> {

    @Query(nativeQuery = true)
    List<AccountDeposit> reverseDepositsList();

    @Override
    AccountDeposit save(AccountDeposit accountDeposit);
}
