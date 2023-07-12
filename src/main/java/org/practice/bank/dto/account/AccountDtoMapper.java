package org.practice.bank.dto.account;

import org.practice.bank.model.Account;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AccountDtoMapper implements Function<Account, AccountDto> {
    @Override
    public AccountDto apply(Account account) {
        return new AccountDto(
                account.getId(),
                account.getAccountNumber(),
                account.getOwner(),
                account.getCurrency(),
                account.getBalance()
        );
    }
}
