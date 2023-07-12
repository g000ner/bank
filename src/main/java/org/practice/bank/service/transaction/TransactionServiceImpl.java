package org.practice.bank.service.transaction;

import lombok.extern.slf4j.Slf4j;
import org.practice.bank.dto.account.AccountDto;
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
    public TransactionDto doTransaction(Account accountFrom, Account accountTo, Double sum)
            throws NegativeTransactionSumException, AccountNotFoundException, NotEnoughCashFundsException, NotEqualCurrencyException {
        AccountDto accountFromDto = accountService.getByNumber(accountFrom.getAccountNumber());
        AccountDto accountToDto = accountService.getByNumber(accountTo.getAccountNumber());

        if (accountFromDto == null || accountToDto == null) {
            throw new AccountNotFoundException();
        }

        if (sum < 0) {
            throw new NegativeTransactionSumException();
        }

        if (! accountFromDto.currency().equals(accountToDto.currency())) {
            throw new NotEqualCurrencyException();
        }

        if (sum > accountFromDto.balance()) {
            throw new NotEnoughCashFundsException();
        }

        accountFrom.setBalance(accountFromDto.balance() - sum);
        accountService.minusBalance(accountFromDto.id(), sum);
        accountFrom.setId(accountFromDto.id());

        accountTo.setBalance(accountToDto.balance() + sum);
        accountService.plusBalance(accountToDto.id(), sum);
        accountTo.setId(accountToDto.id());

        Transaction transactionToDo = new Transaction();
        transactionToDo.setAccountFrom(accountFrom);
        transactionToDo.setAccountTo(accountTo);
        transactionToDo.setSum(sum);
        transactionToDo.setDate(LocalDate.now());
        transactionToDo.setTime(new Time(System.currentTimeMillis()));

        return transactionDtoMapper.apply(transactionRepository.save(transactionToDo));
    }

    @Override
    public List<TransactionDto> getOutgoingTransactionsByAccountNumber(Account account) {
        return transactionRepository.findByAccountFrom(account).
                stream().map(transactionDtoMapper).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> getIncomingTransactionsByAccountNumber(Account account) {
        return transactionRepository.findByAccountTo(account).
                stream().map(transactionDtoMapper).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> getAll() {
        return transactionRepository.findAll().stream().map(transactionDtoMapper).collect(Collectors.toList());
    }

}
