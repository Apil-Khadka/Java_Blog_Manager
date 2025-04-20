package com.apil.java_blog_manager.Service;

import com.apil.java_blog_manager.Entity.Post;
import com.apil.java_blog_manager.Entity.User;
import com.apil.java_blog_manager.Repo.PostRepository;
import com.apil.java_blog_manager.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findPostById(userId);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            if (!post.getTitle().equals(postDetails.getTitle())) {
                post.setTitle(postDetails.getTitle());
            }
            if (!post.getContent().equals(postDetails.getContent())) {
                post.setContent(postDetails.getContent());
            }
            if (!post.getId().equals(postDetails.getId())) {
                post.setId(postDetails.getId());
            }
            return postRepository.save(post);
        }
        return null;
    }


    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Post not found with id: " + id));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userRepository.findUserByUsername(currentUsername);

        // Check if user is the post owner, an admin, or a moderator
        boolean isAdmin = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        boolean isModerator = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MODERATOR"));
        boolean isOwner = post.getUser() != null && currentUser != null && post.getUser().getId().equals(currentUser.getId());

        if (isAdmin || isModerator || isOwner) {
            postRepository.deleteById(id);
        } else {
            throw new AccessDeniedException("You don't have permission to delete this post");
        }
    }

}
