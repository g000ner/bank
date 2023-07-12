package org.practice.bank.service.account;

import lombok.extern.slf4j.Slf4j;
import org.practice.bank.dto.account.AccountDto;
import org.practice.bank.dto.account.AccountDtoMapper;
import org.practice.bank.model.Account;
import org.practice.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountDtoMapper accountDtoMapper;

    @Override
    public AccountDto getById(Long id) {
        return accountRepository.findById(id).map(accountDtoMapper).orElse(null);
    }

    @Override
    public AccountDto getByNumber(String number) {
        return accountRepository.findByAccountNumber(number).map(accountDtoMapper).orElse(null);
    }

    @Override
    public AccountDto save(Account account) {
        return accountDtoMapper.apply(accountRepository.save(account));
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDto> getAll() {
        return accountRepository.findAll().stream().map(accountDtoMapper).collect(Collectors.toList());
    }

    @Override
    public void minusBalance(Long accountId, Double sum) {
        accountRepository.minusBalance(accountId, sum);
    }

    @Override
    public void plusBalance(Long accountId, Double sum) {
        accountRepository.plusBalance(accountId, sum);
    }
}
