package com.apil.java_blog_manager.Repo;

import com.apil.java_blog_manager.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByUsername(String username);

    User findUserByUsername(String username);
}
