package com.apil.java_blog_manager.Config;

import com.apil.java_blog_manager.Entity.User;
import com.apil.java_blog_manager.Repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    @Order(1)
    CommandLineRunner initUser(UserRepository userRepository) {
        return args -> {
            User user1 = new User(
                    "test",
                    bcryptEncodePassword().encode("test")
            );
            User user2 = new User(
                    "test2",
                    bcryptEncodePassword().encode("test2")
            );
            User user3 = new User(
                    "test3",
                    bcryptEncodePassword().encode("test3")
            );
            userRepository.saveAll(List.of(user1, user2, user3));
        };
    }

    @Bean
    public PasswordEncoder bcryptEncodePassword() {
        return new BCryptPasswordEncoder(12);
    }
}
