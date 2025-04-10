package com.apil.java_blog_manager.User;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@Order(1)
public class UserConfig {

    @Bean
    CommandLineRunner initUser(UserRepository userRepository)
    {
        return args -> {
          User user1 = new User(
              "test",
                bcryptEncodePassword().encode("test"),
                "test@gmail.com"
          );
          User user2 = new User(
                  "test2",
                    bcryptEncodePassword().encode("test2"),
                  "test2@gmail.com"
          );
          User user3 = new User(
                  "test3",
                    bcryptEncodePassword().encode("test3"),
                  "test3@gmail.com"
          );
          userRepository.saveAll(List.of(user1,user2,user3));
        };
    }

    @Bean
    public PasswordEncoder bcryptEncodePassword() {
        return new BCryptPasswordEncoder();
    }
}
