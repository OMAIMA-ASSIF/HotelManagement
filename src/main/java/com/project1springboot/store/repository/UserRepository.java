package com.project1springboot.store.repository;
import com.project1springboot.store.entity.User;
import com.project1springboot.store.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository    // composent qui communique avec la base de donnee
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    Optional<User> findByUserRole(UserRole userRole);

}
