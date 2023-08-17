package com.ktds.myspringboot.repository;

import com.ktds.myspringboot.entity.AccountEntity;
import com.sun.jna.platform.win32.Advapi32Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    void select() {
        accountRepository.findAll()
                .forEach(acct -> System.out.println(acct.getUsername()));

        Optional<AccountEntity> optional = accountRepository.findById(10L);
        if (optional.isPresent()){
            AccountEntity account = optional.get();
            assertEquals("spring", account.getUsername());
        } else
        {
            System.out.println("not found");
        }

        AccountEntity accountEntity = accountRepository.findById(1l)
                .orElseThrow(()-> new RuntimeException("Account Not Found"));
        assertEquals("spring", accountEntity.getUsername());

    }

    @Test
    void insert_select() {
        //AccountEntity account = new AccountEntity();
        AccountEntity account = AccountEntity.builder()
                .username("spring")
                .password("test1234")
                .build();
        AccountEntity savedAccount = accountRepository.save(account);

    }

}