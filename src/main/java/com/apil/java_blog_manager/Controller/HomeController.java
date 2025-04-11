package com.apil.java_blog_manager.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Tag(name = "Home", description = "Home API")
public class HomeController {

    @GetMapping
    @Operation(
            description = "Welcome page",
            summary = "Display welcome message",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
            }
    )
    public String homeMessage() {
        return "<div style=\"border:2px solid black; padding:20px; text-align:center; width:50%; margin:50px auto;\">" +
                "<h1 style=\"color:blue;\">Welcome to Blog Manager</h1>" +
                "<p style=\"font-size:16px;\">API is running</p>" +
                "<p style=\"font-style:italic;\">Go to <a href=\"/swagger-ui/index.html\" style=\"color:green;\">/swagger-ui/index.html</a> to access the API Documentation</p>" +
                "</div>";
    }
}
