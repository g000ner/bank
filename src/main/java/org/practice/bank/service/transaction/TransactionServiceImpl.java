package org.practice.bank.service.transaction;

import lombok.extern.slf4j.Slf4j;
import org.practice.bank.dto.transaction.TransactionDto;
import org.practice.bank.dto.transaction.TransactionDtoMapper;
import org.practice.bank.exception.account.AccountNotFoundException;
import org.practice.bank.exception.transaction.NegativeTransactionSumException;
import org.practice.bank.exception.transaction.NotEnoughCashFundsException;
import org.practice.bank.exception.transaction.NotEqualCurrencyException;
import org.practice.bank.model.Account;
import org.practice.bank.model.Transaction;
import org.practice.bank.repository.TransactionRepository;
import org.practice.bank.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionDtoMapper transactionDtoMapper;

    @Transactional
    @Override
    public TransactionDto doTransaction(String accountFromNumber, String accountToNumber, Long sum)
            throws NegativeTransactionSumException, AccountNotFoundException, NotEnoughCashFundsException, NotEqualCurrencyException {
        Account accountFrom = accountService.selectByNumber(accountFromNumber);
        Account accountTo = accountService.selectByNumber(accountToNumber);

        if (accountFrom == null || accountTo == null) {
            throw new AccountNotFoundException();
        }

        if (sum < 0) {
            throw new NegativeTransactionSumException();
        }

        if (! accountFrom.getCurrency().equals(accountTo.getCurrency())) {
            throw new NotEqualCurrencyException();
        }

        if (sum > accountFrom.getBalance()) {
            throw new NotEnoughCashFundsException();
        }

        accountFrom.setBalance(accountFrom.getBalance() - sum);
        accountTo.setBalance(accountTo.getBalance() + sum);

        // todo Обработать исключительные ситуации
        Transaction transactionToDo = new Transaction();
        transactionToDo.setAccountFrom(accountFrom);
        transactionToDo.setAccountTo(accountTo);
        transactionToDo.setSum(sum);
        transactionToDo.setDate(LocalDate.now());
        transactionToDo.setTime(new Time(System.currentTimeMillis()));

        return transactionDtoMapper.apply(transactionRepository.save(transactionToDo));
    }

    @Override
    public List<TransactionDto> getOutgoingTransactionsByAccountNumber(String accountFromNumber) {
        Account accountFrom = accountService.selectByNumber(accountFromNumber);
        return transactionRepository.findByAccountFrom(accountFrom).
                stream().map(transactionDtoMapper).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> getIncomingTransactionsByAccountNumber(String accountToNumber) {
        Account accountTo = accountService.selectByNumber(accountToNumber);
        return transactionRepository.findByAccountTo(accountTo).
                stream().map(transactionDtoMapper).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> getAll() {
        return transactionRepository.findAll().stream().map(transactionDtoMapper).collect(Collectors.toList());
    }

}
