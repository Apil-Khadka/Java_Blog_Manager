package com.apil.java_blog_manager.Repo;

import com.apil.java_blog_manager.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    boolean existsByName(String name);
}