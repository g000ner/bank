package org.practice.bank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
public class Account {

    @Id
    @Column(name = "account_number")
    private String accountNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_owner", referencedColumnName = "id")
    private Client owner;

    @Column(name = "currency")
    private String currency;

    @Column(name = "balance")
    private Long balance;

}
