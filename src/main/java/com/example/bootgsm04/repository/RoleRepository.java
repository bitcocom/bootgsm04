package com.example.bootgsm04.repository;

import com.example.bootgsm04.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String name);
}
