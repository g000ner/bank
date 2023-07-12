package org.practice.bank.dto.transaction;

import org.practice.bank.model.Account;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.time.LocalDate;

public record TransactionDto (
        Long id,
        Account accountFrom,
        Account accountTo,
        LocalDate date,
        Time time,
        Long sum
) {}
