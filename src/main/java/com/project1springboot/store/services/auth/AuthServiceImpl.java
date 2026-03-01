package com.project1springboot.store.services.auth;

import com.project1springboot.store.dto.SignupRequest;
import com.project1springboot.store.dto.UserDto;
import com.project1springboot.store.entity.User;
import com.project1springboot.store.enums.UserRole;
import com.project1springboot.store.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @PostConstruct
    public void createAnAdminAccount(){
        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount.isEmpty()){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("Omaima");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("omaima1234"));
            userRepository.save(user);
            System.out.println("Admin account created successfully");
        }else{
            System.out.println("Admin account already exists");
        }
    }

    public UserDto createUser(SignupRequest signupRequest){
        if(userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()){
            throw new EntityExistsException("User already exists with email " + signupRequest.getEmail());
        }

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createdUser = userRepository.save(user);
        return createdUser.getUserDto();
    }



}
