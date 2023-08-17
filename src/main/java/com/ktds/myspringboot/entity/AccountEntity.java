package com.ktds.myspringboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "account") // table네임 지정, 안하면 account_entity라고 함
@Getter
@Setter
public class AccountEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;
}
