package com.vladbadey.demo.repository;

import java.util.Optional;

import com.vladbadey.demo.enums.ERole;
import com.vladbadey.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}