package com.ktds.myspringboot.config;

import com.ktds.myspringboot.dto.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

@Profile("prod")
@Configuration
public class ProdConfig {
    @Bean("myCustomer") // class명이 아닌 다른 이름으로 Bean을 등록하고 싶을때
    @Scope(value = "singleton")
    public Customer customer() {
        return Customer.builder() // CustomerBuilder 를 사용하면 param순서를 생각하지 않아도 된다.
                .name("ProdMode")
                .age(20)
                .build();
    }
}