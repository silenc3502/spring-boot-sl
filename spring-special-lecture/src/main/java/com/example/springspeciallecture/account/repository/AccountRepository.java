package com.example.springspeciallecture.account.repository;

import com.example.springspeciallecture.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
