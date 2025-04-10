package com.apil.java_blog_manager.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsUserByUsername(String username);

    boolean existsUserByEmail(String email);
}
