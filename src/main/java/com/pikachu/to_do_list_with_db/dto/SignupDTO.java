package com.pikachu.to_do_list_with_db.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {
    @NotBlank(message = "아이디를 입력하세요")
    @Size(message = "아이디는 2~30자여야 합니다.", min=2, max=30)
    private String username;

    @NotBlank(message = "비밀번호를 입력하세요")
    @Size(message = "비밀번호는 5~15자여야 합니다.", min=5, max=15)
    private String password;
}
