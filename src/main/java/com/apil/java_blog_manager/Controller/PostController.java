package com.apil.java_blog_manager.Controller;

import com.apil.java_blog_manager.Entity.Post;
import com.apil.java_blog_manager.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping(path = "user/{user_id}")
    public List<Post> getPostsByUserId(@PathVariable Long user_id) {
        return postService.getPostsByUserId(user_id);
    }

    @GetMapping(path = "{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping(path = "{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        return postService.updatePost(id, postDetails);
    }

    @DeleteMapping(path = "{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
