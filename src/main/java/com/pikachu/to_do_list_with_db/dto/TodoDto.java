package com.pikachu.to_do_list_with_db.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDto {
    private  Integer id;

    @NotBlank(message = "제목을 입력하세요")
    private String title;
    private boolean completed;
}
