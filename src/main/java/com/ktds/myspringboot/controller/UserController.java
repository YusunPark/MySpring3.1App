package com.ktds.myspringboot.controller;

import com.ktds.myspringboot.dto.UserReqDto;
import com.ktds.myspringboot.entity.User;
import com.ktds.myspringboot.repository.UserRepository;
import com.ktds.myspringboot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository; // Autowired 대신에 / @RequiredArgsConstructor + final 하면 됨
    private final UserService userService; // Autowired 대신에 / @RequiredArgsConstructor + final 하면 됨

    // leaf 경로로 호출이 되면 실행이 된다.
    @GetMapping("/leaf")
    public String leaf(Model model) {
        model.addAttribute("name","스프링부트");

        // return 값은 leaf.html을 전해준다는 뜻
        return "leaf";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(UserReqDto user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid UserReqDto user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userService.saveUser(user);
        model.addAttribute("users", userService.getUsers());
        return "index";
    }
}
