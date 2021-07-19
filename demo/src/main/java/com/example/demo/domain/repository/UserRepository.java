package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}
