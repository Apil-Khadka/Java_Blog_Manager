package com.apil.java_blog_manager.Post;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Order(20)
public class PostConfig {

    @Bean
    CommandLineRunner initPost(PostRepository postRepository) {
        return args -> {
            Post post1 = new Post("Post 1", "Content of post 1", 1L);
            Post post2 = new Post("Post 2", "Content of post 2", 2L);
            Post post3 = new Post("Post 3", "Content of post 3", 3L);
            Post post4 = new Post("Post 4", "Content of post 4", 2L);
            Post post5 = new Post("Post 5", "Content of post 5", 1L);

            postRepository.saveAll(List.of(post1, post2, post3, post4, post5));
        };
    }
}
