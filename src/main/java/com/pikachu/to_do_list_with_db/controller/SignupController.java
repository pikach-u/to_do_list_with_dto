package com.pikachu.to_do_list_with_db.controller;

import com.pikachu.to_do_list_with_db.dto.SignupDTO;
import com.pikachu.to_do_list_with_db.model.User;
import com.pikachu.to_do_list_with_db.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignupController {
    private final UserRepository userRepository;

    @GetMapping("/signup")
    public String showSignup(Model model){
        model.addAttribute("signupDto", new SignupDTO());

        return "signup";
    }

    @PostMapping("/signup")
    public String doiSignup(    // 회원가입 시 데이터 검증을 위한 어노테이션
            @Valid @ModelAttribute SignupDTO signupDTO,
            BindingResult bindingResult,
            Model model
    ){
        if(bindingResult.hasErrors()){
            return "signup";
        }
        // 중복 가입 여부 체크

        User user = User.builder()
                .username(signupDTO.getUsername())
                .password(signupDTO.getPassword())
                .build();

        return "redirect:/login?resistered"; // http://localhost:8080/login?registered=true
    }

}
