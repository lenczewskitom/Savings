package com.kodilla.savings.exception.notFound;

import com.kodilla.savings.domain.CryptoOrder;
import com.kodilla.savings.exception.EntityNotFoundException;

public class CryptoOrderNotFoundException extends EntityNotFoundException {

    public CryptoOrderNotFoundException() {
        super(CryptoOrder.class);
    }
}
