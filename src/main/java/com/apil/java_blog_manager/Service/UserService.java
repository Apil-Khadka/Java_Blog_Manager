package com.apil.java_blog_manager.Service;

import com.apil.java_blog_manager.Entity.User;
import com.apil.java_blog_manager.Repo.UserRepository;
import com.apil.java_blog_manager.Security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncodePassword;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder bcryptEncodePassword) {
        this.userRepository = userRepository;
        this.bcryptEncodePassword = bcryptEncodePassword;
    }


/*
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
*/

    public User registerUser(User user) {
        User saveUser = new User();
        saveUser.setUsername(user.getUsername());
        saveUser.setPassword(bcryptEncodePassword.encode(user.getPassword()));
        return userRepository.save(saveUser);
    }


    public User updateUser(Long id, User user) {
        Optional<User> existingUserOpt = userRepository.findById(id);

        if (existingUserOpt.isEmpty()) {
            throw new IllegalStateException("User not found");
        }

        User existingUser = existingUserOpt.get();

        if (user.getUsername() != null && !user.getUsername().equals(existingUser.getUsername())) {
            if (userRepository.existsUserByUsername(user.getUsername())) {
                throw new IllegalStateException("Username already taken");
            }
            existingUser.setUsername(user.getUsername());
        }

        if (user.getPassword() != null && !bcryptEncodePassword.matches(user.getPassword(), existingUser.getPassword())) {
            existingUser.setPassword(bcryptEncodePassword.encode(user.getPassword()));
        }


        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("User with id " + id + " does not exist");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrinciple(user);
    }
}
