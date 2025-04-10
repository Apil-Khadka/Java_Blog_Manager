package com.apil.java_blog_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JavaBlogManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaBlogManagerApplication.class, args);
    }

    @GetMapping
    public String hello() {
        return "<h1>Welcome to Java Blog Manager</h1>" +
                "<p>API is running</p>" +
                "<p>Use <a href=\"/api/posts\">/api/posts</a> to access the posts API</p>"
                ;
    }
}
