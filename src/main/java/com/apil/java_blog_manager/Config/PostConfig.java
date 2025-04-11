package com.apil.java_blog_manager.Config;

import com.apil.java_blog_manager.Entity.Post;
import com.apil.java_blog_manager.Entity.User;
import com.apil.java_blog_manager.Repo.PostRepository;
import com.apil.java_blog_manager.Repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller

public class PostConfig {

    @Bean
    @DependsOnDatabaseInitialization
    @Order(3)
    CommandLineRunner initPost(PostRepository postRepository, UserRepository userRepository) {
        return args -> {
            User user1 = userRepository.findById(1L).orElseThrow();
            User user2 = userRepository.findById(2L).orElseThrow();
            User user3 = userRepository.findById(3L).orElseThrow();

            Post post1 = new Post("Post 1", "Content of post 1", user1.getId());
            Post post2 = new Post("Post 2", "Content of post 2", user2.getId());
            Post post3 = new Post("Post 3", "Content of post 3", user3.getId());
            Post post4 = new Post("Post 4", "Content of post 4", user2.getId());
            Post post5 = new Post("Post 5", "Content of post 5", user1.getId());

            postRepository.saveAll(List.of(post1, post2, post3, post4, post5));
        };
    }
}
