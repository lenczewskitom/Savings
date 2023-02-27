package com.kodilla.savings.repository;

import com.kodilla.savings.domain.CryptoTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CryptoTransactionRepository extends CrudRepository<CryptoTransaction, Long> {

    @Query()
    List<CryptoTransaction> reverseCryptoTransactionsList();

    @Override
    CryptoTransaction save(CryptoTransaction cryptoTransaction);
}
