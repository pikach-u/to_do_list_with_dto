package com.pikachu.to_do_list_with_db.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Integer id;
    private String username;
    private String password;
}
