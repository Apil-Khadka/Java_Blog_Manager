package com.apil.java_blog_manager.DTO;

/**
 * Data Transfer Object for Post responses
 * Using Java record for immutability and reduced boilerplate
 */
public record PostResponseDTO(
    Long id,
    String title,
    String content,
    Long userId,
    String username
) {}
