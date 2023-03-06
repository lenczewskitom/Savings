package com.kodilla.savings.exception.notFound;

import com.kodilla.savings.domain.CurrencyOrder;
import com.kodilla.savings.exception.EntityNotFoundException;

public class CurrencyOrderNotFoundException extends EntityNotFoundException {

    public CurrencyOrderNotFoundException() {

        super(CurrencyOrder.class);
    }
}
