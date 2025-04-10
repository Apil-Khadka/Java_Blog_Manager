package com.apil.java_blog_manager.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
           if(!post.getTitle().equals(postDetails.getTitle())) {
                post.setTitle(postDetails.getTitle());
            }
            if(!post.getContent().equals(postDetails.getContent())) {
                post.setContent(postDetails.getContent());
            }
            if(!post.getUser_id().equals(postDetails.getUser_id())) {
                post.setUser_id(postDetails.getUser_id());
            }
            return postRepository.save(post);
        }
        return null;
    }


    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

}
