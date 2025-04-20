package com.apil.java_blog_manager.DTO;

/**
 * Data Transfer Object for Post creation and update requests
 * Using Java record for immutability and reduced boilerplate
 */
public record PostRequestDTO(
    String title,
    String content,
    Long userId
) {}
