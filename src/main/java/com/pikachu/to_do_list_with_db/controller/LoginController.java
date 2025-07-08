package com.pikachu.to_do_list_with_db.controller;

import com.pikachu.to_do_list_with_db.dto.LoginDto;
import com.pikachu.to_do_list_with_db.dto.SignupDTO;
import com.pikachu.to_do_list_with_db.model.User;
import com.pikachu.to_do_list_with_db.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
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
public class LoginController {
    private final UserRepository userRepository;

    @GetMapping({"/", "/login"})
    public String showLogin(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(
            @Valid @ModelAttribute("loginDto") LoginDto loginDto,
            BindingResult bindingResult,
            HttpSession httpSession,    //Login에는 서버에 알려주기 위해 Session 필요
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        try {
            User user = userRepository.findByUsername(loginDto.getUsername());

            if (!user.getPassword().equals(loginDto.getPassword())) {
                model.addAttribute("error", "비밀번호가 올바르지 않습니다.");

                return "login";
            }

            httpSession.setAttribute("user", user);     // user 테이블에 현재 만들어진 User추가
            //개발자도구 Application에서 JSESSIONID를 확인할 수 있음
            
            return "redirect:/todos";
        } catch (Exception e) {
            model.addAttribute("error", "존재하지 않는 사용자입니다.");

            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/login";
    }
}
