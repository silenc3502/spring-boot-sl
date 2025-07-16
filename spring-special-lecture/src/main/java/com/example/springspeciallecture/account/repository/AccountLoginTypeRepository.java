package com.example.springspeciallecture.account.repository;

import com.example.springspeciallecture.account.entity.AccountLoginType;
import com.example.springspeciallecture.account.entity.LoginType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountLoginTypeRepository extends JpaRepository<AccountLoginType, Long> {
    Optional<AccountLoginType> findByLoginType(LoginType loginType);
}
