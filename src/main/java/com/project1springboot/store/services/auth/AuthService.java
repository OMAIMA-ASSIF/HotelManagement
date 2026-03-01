package com.project1springboot.store.services.auth;

import com.project1springboot.store.dto.SignupRequest;
import com.project1springboot.store.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);
}
