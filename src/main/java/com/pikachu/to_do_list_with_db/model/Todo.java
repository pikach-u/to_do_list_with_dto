package com.pikachu.to_do_list_with_db.model;

import lombok.Data;

@Data
public class Todo {
    private Integer id;
    private String title;
    private boolean completed;
    private Integer userId;
}
