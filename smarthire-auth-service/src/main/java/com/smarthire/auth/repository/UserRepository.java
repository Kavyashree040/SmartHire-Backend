package com.smarthire.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarthire.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
