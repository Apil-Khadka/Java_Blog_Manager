package com.apil.java_blog_manager.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String hello() {
        return "<h1>Welcome to Java Blog Manager</h1>" +
                "<p>API is running</p>" +
                "<p>Use <a href=\"/api/posts\">/api/posts</a> to access the posts API</p>"
                ;
    }
}
