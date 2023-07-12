package org.practice.bank.dto.transaction;

import org.practice.bank.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TransactionDtoMapper implements Function<Transaction, TransactionDto> {
    @Override
    public TransactionDto apply(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getAccountFrom(),
                transaction.getAccountTo(),
                transaction.getDate(),
                transaction.getTime(),
                transaction.getSum()
        );
    }
}
