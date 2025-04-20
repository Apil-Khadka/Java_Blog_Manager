package com.apil.java_blog_manager.Controller;

import com.apil.java_blog_manager.Entity.Post;
import com.apil.java_blog_manager.Service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Tag(name = "Post", description = "Post API")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @Operation(
            description = "Get all posts",
            summary = "Retrieve all posts",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
            }
    )
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping(path = "user/{user_id}")
    @Operation(
            description = "Get posts by user ID",
            summary = "Retrieve all posts for a specific user",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
            }
    )
    public List<Post> getPostsByUserId(@PathVariable Long user_id) {
        return postService.getPostsByUserId(user_id);
    }

    @GetMapping(path = "{id}")
    @Operation(
            description = "Get post by ID",
            summary = "Retrieve a specific post by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
            }
    )
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    @Operation(
            description = "Create post",
            summary = "Create a new post",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
            }
    )
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping(path = "{id}")
    @Operation(
            description = "Update post",
            summary = "Update an existing post by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
            }
    )
    public Post updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        return postService.updatePost(id, postDetails);
    }

    @DeleteMapping(path = "{id}")
    @Operation(
            description = "Delete post",
            summary = "Delete a post by ID. Only the post owner, admin, or moderator can delete a post.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            description = "Forbidden - User doesn't have permission to delete this post",
                            responseCode = "403"
                    ),
                    @ApiResponse(
                            description = "Not Found - Post not found",
                            responseCode = "404"
                    )
            }
    )
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
