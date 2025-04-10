package com.apil.java_blog_manager.Security;

import com.apil.java_blog_manager.Entity.User;
import com.apil.java_blog_manager.Service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Autowired
    public ApplicationService(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String loginUser(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user);
        }
        return "fail";
    }

}
