package org.practice.bank.service.account;

import org.practice.bank.dto.account.AccountDto;
import org.practice.bank.model.Account;

import java.util.List;

public interface AccountService {

    AccountDto getById(Long id);

    AccountDto getByNumber(String number);
    AccountDto save(Account account);
    void delete(Long id);
    List<AccountDto> getAll();

    void minusBalance(Long accountId, Double sum);
    void plusBalance(Long accountId, Double sum);


}
