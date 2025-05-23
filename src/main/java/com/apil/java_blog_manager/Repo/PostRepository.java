package com.apil.java_blog_manager.Repo;

import com.apil.java_blog_manager.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostById(Long userId);

}
