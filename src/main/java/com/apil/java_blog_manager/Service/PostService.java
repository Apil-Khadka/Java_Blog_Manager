package com.apil.java_blog_manager.Service;

import com.apil.java_blog_manager.DTO.PostRequestDTO;
import com.apil.java_blog_manager.DTO.PostResponseDTO;
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
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // Convert Post entity to PostResponseDTO
    private PostResponseDTO convertToDTO(Post post) {
        if (post == null) return null;

        String username = post.getUser() != null ? post.getUser().getUsername() : null;
        Long userId = post.getUser() != null ? post.getUser().getId() : null;

        return new PostResponseDTO(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            userId,
            username
        );
    }

    // Convert PostRequestDTO to Post entity
    private Post convertToEntity(PostRequestDTO dto) {
        if (dto == null) return null;

        User user = null;
        if (dto.userId() != null) {
            user = userRepository.findById(dto.userId()).orElse(null);
        }

        Post post = new Post();
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setUser(user);

        return post;
    }

    // Get all posts as DTOs
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // Get post by ID as DTO
    public PostResponseDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        return convertToDTO(post);
    }

    // Get posts by user ID as DTOs
    public List<PostResponseDTO> getPostsByUserId(Long userId) {
        return postRepository.findPostById(userId).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // Create post from DTO
    public PostResponseDTO createPost(PostRequestDTO postDTO) {
        Post post = convertToEntity(postDTO);
        Post savedPost = postRepository.save(post);
        return convertToDTO(savedPost);
    }

    // Update post from DTO
    public PostResponseDTO updatePost(Long id, PostRequestDTO postDTO) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setTitle(postDTO.title());
            post.setContent(postDTO.content());

            // Update user if userId has changed
            if (postDTO.userId() != null) {
                User user = userRepository.findById(postDTO.userId()).orElse(null);
                post.setUser(user);
            }

            Post updatedPost = postRepository.save(post);
            return convertToDTO(updatedPost);
        }
        return null;
    }


    public boolean deletePost(Long id) {
        try {
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
                return true;
            } else {
                throw new AccessDeniedException("You don't have permission to delete this post");
            }
        } catch (IllegalStateException | AccessDeniedException e) {
            throw e; // Re-throw these exceptions to be handled by the controller
        } catch (Exception e) {
            return false;
        }
    }

    // For backward compatibility
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // For backward compatibility
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

}
