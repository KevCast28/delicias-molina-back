package com.deliciasmolina.deliciasmolinaapi.repository;

import com.deliciasmolina.deliciasmolinaapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsernameIgnoreCase(String username);

    List<User> findByIsActiveTrue();

    Optional<User> findByUsername(String username);
}
