package org.practice.bank.rest;

import org.practice.bank.dto.account.AccountDto;
import org.practice.bank.dto.transaction.TransactionDto;
import org.practice.bank.model.Account;
import org.practice.bank.model.Client;
import org.practice.bank.model.Transaction;
import org.practice.bank.request.AccountRequestBody;
import org.practice.bank.service.account.AccountService;
import org.practice.bank.service.client.ClientService;
import org.practice.bank.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountRestControllerV1 {

    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> getAccount(@PathVariable String accountNumber) {
        if (accountNumber == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AccountDto account = accountService.getByNumber(accountNumber);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> saveAccount(@RequestBody @Valid AccountRequestBody accountRequestBody) {
        if (accountRequestBody == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Client owner = clientService.selectById(accountRequestBody.getOwnerId());
        if (owner == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Account account = new Account();
        account.setAccountNumber(accountRequestBody.getAccountNumber());
        account.setOwner(owner);
        account.setBalance(accountRequestBody.getBalance());
        account.setCurrency(accountRequestBody.getCurrency());

        AccountDto accountDto = accountService.save(account);
        return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> updateAccount(@RequestBody @Valid AccountRequestBody accountRequestBody) {
        if (accountRequestBody == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Client owner = clientService.selectById(accountRequestBody.getOwnerId());
        if (owner == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Account account = new Account();
        account.setOwner(owner);
        account.setBalance(accountRequestBody.getBalance());
        account.setCurrency(accountRequestBody.getCurrency());

        AccountDto accountDto = accountService.save(account);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable String accountNumber) {
        if (accountNumber == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        AccountDto account = accountService.getByNumber(accountNumber);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        accountService.delete(accountNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountDto>> getAllAccount() {
        List<AccountDto> accounts = accountService.getAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @RequestMapping(value = "{accountNumber}/transactions/outgoing",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDto>> getOutgoingTransactionsForAccount(@PathVariable("accountNumber") String accountNumber) {
        if (accountNumber == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<TransactionDto> transactions = transactionService.getOutgoingTransactionsByAccountNumber(accountNumber);
        if (transactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @RequestMapping(value = "{accountNumber}/transactions/incoming",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDto>> getIncomingTransactionsForAccount(@PathVariable("accountNumber") String accountNumber) {
        if (accountNumber == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<TransactionDto> transactions = transactionService.getIncomingTransactionsByAccountNumber(accountNumber);
        if (transactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

}
