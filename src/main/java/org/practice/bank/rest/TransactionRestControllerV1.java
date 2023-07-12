package org.practice.bank.rest;

import org.practice.bank.dto.transaction.TransactionDto;
import org.practice.bank.request.TransactionRequestBody;
import org.practice.bank.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionRestControllerV1 {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDto> saveTransaction(@RequestBody @Valid TransactionRequestBody transactionRequestBody) {
        if (transactionRequestBody == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        TransactionDto transaction = null;

        transaction = transactionService.doTransaction(
                transactionRequestBody.getAccountFrom(),
                transactionRequestBody.getAccountTo(), transactionRequestBody.getSum());
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TransactionDto>> getAll() {
        List<TransactionDto> transactions = transactionService.getAll();
        if (transactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
