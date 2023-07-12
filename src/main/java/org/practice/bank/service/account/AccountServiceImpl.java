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
    public AccountDto getByNumber(String number) {
        return accountRepository.findById(number).map(accountDtoMapper).orElse(null);
    }

    @Override
    public Account selectByNumber(String number) {
        return accountRepository.findById(number).orElse(null);
    }

    @Override
    public AccountDto save(Account account) {
        return accountDtoMapper.apply(accountRepository.save(account));
    }

    @Override
    public void delete(String number) {
        accountRepository.deleteById(number);
    }

    @Override
    public List<AccountDto> getAll() {
        return accountRepository.findAll().stream().map(accountDtoMapper).collect(Collectors.toList());
    }

}
