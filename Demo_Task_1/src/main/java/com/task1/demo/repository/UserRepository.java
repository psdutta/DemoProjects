package com.task1.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task1.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
