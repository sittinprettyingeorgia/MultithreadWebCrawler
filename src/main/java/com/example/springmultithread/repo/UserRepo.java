package com.example.springmultithread.repo;


import com.example.springmultithread.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User getUserById(Long id);
    User getUserByUsername(String username);
}
