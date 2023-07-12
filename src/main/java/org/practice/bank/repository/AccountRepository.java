package org.practice.bank.repository;

import org.practice.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String number);

    @Modifying
    @Query("UPDATE Account a SET a.balance = a.balance - :sum WHERE a.id=:id")
    void minusBalance(@Param("id") Long id, @Param("sum") Double sum);

    @Modifying
    @Query("UPDATE Account a SET a.balance = a.balance + :sum WHERE a.id=:id")
    void plusBalance(@Param("id") Long id, @Param("sum") Double sum);
}
