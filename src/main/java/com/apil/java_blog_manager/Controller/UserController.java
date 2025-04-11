package com.apil.java_blog_manager.Controller;

import com.apil.java_blog_manager.Entity.User;
import com.apil.java_blog_manager.Security.ApplicationService;
import com.apil.java_blog_manager.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User", description = "User API")
public class UserController {

    private final UserService userService;
    private final ApplicationService applicationService;

    @Autowired
    public UserController(UserService userService, ApplicationService applicationService) {
        this.userService = userService;
        this.applicationService = applicationService;
    }

    @GetMapping
    @Operation(
            description = "Get all users",
            summary = "Retrieve all users",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
            }
    )
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{id}")
    @Operation(
            description = "Get user by ID",
            summary = "Retrieve a specific user by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
            }
    )
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(
            description = "Register user",
            summary = "Register user",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
            }
    )
    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    @Operation(
            description = "Login user",
            summary = "Authenticate user and return token",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
            }
    )
    public String loginUser(@RequestBody User user) {
        System.out.println(user);
        return applicationService.loginUser(user);
    }

    @PutMapping(path = "{id}")
    @Operation(
            description = "Update user",
            summary = "Update an existing user by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
            }
    )
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(path = "{id}")
    @Operation(
            description = "Delete user",
            summary = "Delete a user by ID",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "204"
                    ),
            }
    )
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
