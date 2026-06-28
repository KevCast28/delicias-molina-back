package com.deliciasmolina.deliciasmolinaapi.repository;

import com.deliciasmolina.deliciasmolinaapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsernameIgnoreCase(String username);
}
