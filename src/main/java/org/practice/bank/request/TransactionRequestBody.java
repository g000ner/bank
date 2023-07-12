package org.practice.bank.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionRequestBody {

    private String accountFromNumber;
    private String accountToNumber;
    private Long sum;

}
