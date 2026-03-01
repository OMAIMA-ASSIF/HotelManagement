package com.project1springboot.store.dto;

import com.project1springboot.store.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private UserRole userRole;
}
