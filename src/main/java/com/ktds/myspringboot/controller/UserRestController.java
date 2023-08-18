package com.ktds.myspringboot.controller;

import com.ktds.myspringboot.dto.UserReqDto;
import com.ktds.myspringboot.dto.UserResDto;
import com.ktds.myspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    이렇게 이름을 바꿔주는 것도 가능
//    @GetMapping("/{myId}")
//    public UserResDto getUserById(@PathVariable("myId") Long id){
//
//    }

    @GetMapping("/{id}")
    public UserResDto getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping()
    public List<UserResDto> getUser() {
        return userService.getUsers();
    }

    @PatchMapping("/{email}")
    public UserResDto updateUser(@PathVariable String email,
                                 @RequestBody UserReqDto userReqDto){
        return userService.updateUser(email, userReqDto);
    }

}
