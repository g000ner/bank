package org.practice.bank.handler;

import org.practice.bank.exception.account.AccountNotFoundException;
import org.practice.bank.exception.transaction.NegativeTransactionSumException;
import org.practice.bank.exception.transaction.NotEnoughCashFundsException;
import org.practice.bank.exception.transaction.NotEqualCurrencyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NegativeTransactionSumException.class)
    public void negativeTransactionSum() {
        
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AccountNotFoundException.class)
    public void accountNotFound() {

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotEnoughCashFundsException.class)
    public void notEnoughCashFunds() {

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotEqualCurrencyException.class)
    public void notEqualCurrency() {

    }

}
