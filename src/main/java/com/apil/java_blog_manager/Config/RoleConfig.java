package com.apil.java_blog_manager.Config;

import com.apil.java_blog_manager.Entity.Role;
import com.apil.java_blog_manager.Repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class RoleConfig {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            // Create default roles if they don't exist
            List<String> defaultRoles = Arrays.asList("USER", "MODERATOR", "ADMIN");
            
            for (String roleName : defaultRoles) {
                if (!roleRepository.existsByName(roleName)) {
                    Role role = new Role(roleName);
                    roleRepository.save(role);
                    System.out.println("Created role: " + roleName);
                }
            }
        };
    }
}