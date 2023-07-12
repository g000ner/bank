package org.practice.bank.service.transaction;

import org.practice.bank.dto.transaction.TransactionDto;
import org.practice.bank.exception.account.AccountNotFoundException;
import org.practice.bank.exception.transaction.NegativeTransactionSumException;
import org.practice.bank.exception.transaction.NotEnoughCashFundsException;
import org.practice.bank.exception.transaction.NotEqualCurrencyException;
import org.practice.bank.model.Transaction;

import java.util.List;

public interface TransactionService {

    TransactionDto doTransaction(String accountFromNumber, String accountToNumber, Long sum) throws NegativeTransactionSumException, AccountNotFoundException, NotEnoughCashFundsException, NotEqualCurrencyException;
    List<TransactionDto> getOutgoingTransactionsByAccountNumber(String accountFrom);
    List<TransactionDto> getIncomingTransactionsByAccountNumber(String accountTo);
    List<TransactionDto> getAll();

}
