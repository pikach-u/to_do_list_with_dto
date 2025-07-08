package com.pikachu.to_do_list_with_db.controller;

import com.pikachu.to_do_list_with_db.model.User;
import com.pikachu.to_do_list_with_db.repository.TodoRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoRepository todoRepository;

    private User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    @GetMapping
    public String list(HttpSession httpSession) {
        System.out.println(getCurrentUser(httpSession));

        return "todo-list";
    }
}
