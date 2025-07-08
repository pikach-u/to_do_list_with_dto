package com.pikachu.to_do_list_with_db.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {
    @NotBlank(message = "아이디를 입력하세요.")   //공백포함체크
    @Size(message = "아이디는 2~30자여야 합니다.", min=2, max=30)    //길이제한
    private String username;

    @NotBlank(message = "비밀번호를 입력하세요.")   //공백포함체크
    @Size(message = "비밀번호는 8~15자여야 합니다.", min=8, max=15)    //길이제한
    private String password;
    //builder를 써서 mapstructor 이용해서 DTO 만들어도 됨
}
