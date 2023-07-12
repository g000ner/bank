package org.practice.bank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.practice.bank.model.Account;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_from")
    private Account accountFrom;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_to")
    private Account accountTo;

    @Column(name = "date")
    private LocalDate date;
    @Column(name = "time")
    private Time time;
    @Column(name = "sum")
    private Long sum;
    
}
