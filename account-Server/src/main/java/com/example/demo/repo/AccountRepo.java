package com.example.demo.repo;

import com.example.demo.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {
    List<Account> findByCustomerId(Integer id);

    Account findByAccountId(Integer id);
}
