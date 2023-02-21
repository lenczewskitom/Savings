package com.kodilla.savings.repository;

import com.kodilla.savings.domain.CryptoTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoTransactionRepository extends CrudRepository<CryptoTransaction, Long> {
}
