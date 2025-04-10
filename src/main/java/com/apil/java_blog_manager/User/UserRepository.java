package com.apil.java_blog_manager.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsUserByUsername(String username);

    boolean existsUserByEmail(String email);

    User findUserByUsername(String username);
}
