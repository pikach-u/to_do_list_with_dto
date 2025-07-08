package com.pikachu.to_do_list_with_db.controller;

import com.pikachu.to_do_list_with_db.dto.TodoDto;
import com.pikachu.to_do_list_with_db.model.Todo;
import com.pikachu.to_do_list_with_db.model.User;
import com.pikachu.to_do_list_with_db.repository.TodoRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoRepository todoRepository;

    private User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    @GetMapping
    public String list(HttpSession httpSession, Model model) {
        //System.out.println(getCurrentUser(httpSession));
        User user = getCurrentUser(httpSession);

        if (user == null) {   // 세션이 안맞는 경우
            return "redirect:/login";
        }

        List<Todo> list = todoRepository.findAllByUserId(user.getId());
        model.addAttribute("todos", list);

        return "todo-list";
    }

    @GetMapping("/add")
    public String addForm(HttpSession httpSession, Model model) {
        if (getCurrentUser(httpSession) == null) return "redirect:/login";

        model.addAttribute("todoDto", new TodoDto());

        return "todo-form";
    }

    @PostMapping("/add")
    public String add(
            @Valid @ModelAttribute TodoDto todoDto,
            BindingResult bindingResult,
            HttpSession httpSession
    ) {
        if (bindingResult.hasErrors()) return "todo-form";

        //user가 없으면 Getmapping 선에서 걸러짐
        User user = getCurrentUser(httpSession);
        Todo todo = Todo.builder()
                .userId(user.getId())
                .title(todoDto.getTitle())
                .completed(todoDto.isCompleted())
                .build();
        todoRepository.save(todo);

        return "redirect:/todos";
    }

    @GetMapping("/edit/{id}")
    public String editForm(
            @PathVariable int id,
            Model model,
            HttpSession httpSession
    ){
        User user = getCurrentUser(httpSession);

        if ( user == null) return "redirect:/login";

        Todo todo = todoRepository.findByIdAndUserId(id,user.getId());
        TodoDto dto = new TodoDto();
        dto.setId(todo.getId());
        dto.setTitle(todo.getTitle());
        dto.setCompleted(todo.isCompleted());

        model.addAttribute("todoDto", dto);

        return "todo-form";
    }

    @PostMapping("/edit")
    public String edit(
            @Valid @ModelAttribute TodoDto todoDto,
            BindingResult bindingResult,
            HttpSession httpSession
    ) {
        if(bindingResult.hasErrors()) return "todo-form";

        User user = getCurrentUser(httpSession);
        Todo todo = Todo.builder()
                .id(todoDto.getId())
                .title(todoDto.getTitle())
                .completed(todoDto.isCompleted())
                .userId(user.getId())
                .build();

        todoRepository.update(todo);

        return "redirect:/todos";
    }

    @PostMapping("/delete/{id}")
    public String delete(
            @PathVariable int id,
            HttpSession httpSession
    ) {
        User user = getCurrentUser(httpSession);
        todoRepository.deleteByIdAndUserId(id, user.getId());

        return "redirect:/todos";
    }
}
