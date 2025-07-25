package com.stzelas.gr.spring_api.repositories;

import com.stzelas.gr.spring_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
