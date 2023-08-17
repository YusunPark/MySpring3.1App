package com.ktds.myspringboot.repository;

import com.ktds.myspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User, Long> {
    List<User> findByName(String name);         // 중복가능하니 List
    Optional<User> findByEmail(String email);   // null일 수 있으니 optional

}
