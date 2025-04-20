package com.apil.java_blog_manager.Controller;

import com.apil.java_blog_manager.DTO.PostRequestDTO;
import com.apil.java_blog_manager.DTO.PostResponseDTO;
import com.apil.java_blog_manager.Entity.Post;
import com.apil.java_blog_manager.Service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        List<PostResponseDTO> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
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
    public ResponseEntity<List<PostResponseDTO>> getPostsByUserId(@PathVariable Long user_id) {
        List<PostResponseDTO> posts = postService.getPostsByUserId(user_id);
        return ResponseEntity.ok(posts);
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
                    @ApiResponse(
                            description = "Not Found - Post not found",
                            responseCode = "404"
                    )
            }
    )
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long id) {
        PostResponseDTO post = postService.getPostById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(post);
    }

    @PostMapping
    @Operation(
            description = "Create post",
            summary = "Create a new post",
            responses = {
                    @ApiResponse(
                            description = "Created",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Bad Request - Invalid input",
                            responseCode = "400"
                    )
            }
    )
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostRequestDTO postDTO) {
        PostResponseDTO createdPost = postService.createPost(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
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
                    @ApiResponse(
                            description = "Not Found - Post not found",
                            responseCode = "404"
                    )
            }
    )
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long id, @RequestBody PostRequestDTO postDTO) {
        PostResponseDTO updatedPost = postService.updatePost(id, postDTO);
        if (updatedPost == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping(path = "{id}")
    @Operation(
            description = "Delete post",
            summary = "Delete a post by ID. Only the post owner, admin, or moderator can delete a post.",
            responses = {
                    @ApiResponse(
                            description = "No Content - Successfully deleted",
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
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        try {
            boolean deleted = postService.deletePost(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (IllegalStateException e) {
            return ResponseEntity.notFound().build();
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
