package com.ktds.myspringboot.controller;

import com.ktds.myspringboot.dto.UserReqDto;
import com.ktds.myspringboot.dto.UserResDto;
import com.ktds.myspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {
    private final UserService userService;

    @PostMapping
    // @RequestBody : post에서 json으로 데이터를 받을 때 필요
    public UserResDto saveUser(@RequestBody UserReqDto userReqDto){
        return userService.saveUser(userReqDto);
    }

}
