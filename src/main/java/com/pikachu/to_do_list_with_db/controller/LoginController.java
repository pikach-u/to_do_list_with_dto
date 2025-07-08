package com.pikachu.to_do_list_with_db.controller;

import com.pikachu.to_do_list_with_db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserRepository userRepository;

    @GetMapping({"/", "/login"})
    public String showLogin(){
        return "login";
    }
}
