package org.practice.bank.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.practice.bank.model.Client;

@Getter
@Setter
@ToString
public class AccountRequestBody {

    private String accountNumber;
    private Client owner;
    private String currency;
    private Double balance;

}
