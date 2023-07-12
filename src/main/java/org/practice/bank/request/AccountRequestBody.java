package org.practice.bank.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountRequestBody {

    private String accountNumber;
    private Long ownerId;
    private String currency;
    private Long balance;

}
