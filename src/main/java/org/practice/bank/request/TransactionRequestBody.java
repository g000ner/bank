package org.practice.bank.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.practice.bank.model.Account;

@Getter
@Setter
@ToString
public class TransactionRequestBody {

    private Account accountFrom;
    private Account accountTo;
    private Double sum;

}
