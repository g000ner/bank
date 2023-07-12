package org.practice.bank.dto.account;

import org.practice.bank.model.Client;

import javax.persistence.*;

public record AccountDto (
        Long id,
        String accountNumber,
        Client owner,
        String currency,
        Double balance
) {}
