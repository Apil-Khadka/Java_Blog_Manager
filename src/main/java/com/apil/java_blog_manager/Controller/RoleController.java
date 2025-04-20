package com.apil.java_blog_manager.Controller;

import com.apil.java_blog_manager.Entity.Role;
import com.apil.java_blog_manager.Entity.User;
import com.apil.java_blog_manager.Repo.RoleRepository;
import com.apil.java_blog_manager.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/roles")
public class RoleController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Role createRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    @PutMapping("/{userId}/{roleName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> assignRoleToUser(@PathVariable Long userId, @PathVariable String roleName) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            return ResponseEntity.badRequest().body("Role not found");
        }

        User user = userOpt.get();
        user.addRole(role);
        userRepository.save(user);

        return ResponseEntity.ok().body("Role assigned successfully");
    }

    @DeleteMapping("/{userId}/{roleName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> removeRoleFromUser(@PathVariable Long userId, @PathVariable String roleName) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            return ResponseEntity.badRequest().body("Role not found");
        }

        User user = userOpt.get();
        user.removeRole(role);
        userRepository.save(user);

        return ResponseEntity.ok().body("Role removed successfully");
    }
}