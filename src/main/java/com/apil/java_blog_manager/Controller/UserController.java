package com.apil.java_blog_manager.Controller;

import com.apil.java_blog_manager.Entity.User;
import com.apil.java_blog_manager.Security.ApplicationService;
import com.apil.java_blog_manager.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
/*
    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }*/


    @PostMapping("/register")
    @Operation(
            description = "Register user",
            summary = "Register a new user. The USER role will be automatically assigned regardless of any roles provided.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Bad Request - Invalid user data",
                            responseCode = "400"
                    )
            }
    )
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    @Operation(
            description = "Login user",
            summary = "logins user, don't send id.",
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
            summary = "Update user's username and password. Only administrators can update user roles. Admin role cannot be assigned through the API.",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Not Found - User not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Forbidden - Only administrators can update user roles",
                            responseCode = "403"
                    ),
                    @ApiResponse(
                            description = "Bad Request - Username already taken",
                            responseCode = "400"
                    )
            }
    )
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(path = "{id}")
    @Operation(
            description = "Delete user",
            summary = "delete user",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
            }
    )
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
