package org.practice.bank.service.account;

import org.practice.bank.dto.account.AccountDto;
import org.practice.bank.model.Account;

import java.util.List;

public interface AccountService {

    AccountDto getByNumber(String number);

    Account selectByNumber(String number);
    AccountDto save(Account account);
    void delete(String number);
    List<AccountDto> getAll();

}
