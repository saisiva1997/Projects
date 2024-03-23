package com.example.bankingproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingproject.Entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
