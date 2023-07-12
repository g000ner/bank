package org.practice.bank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "passport_seria")
    private String passportSeria;

}
