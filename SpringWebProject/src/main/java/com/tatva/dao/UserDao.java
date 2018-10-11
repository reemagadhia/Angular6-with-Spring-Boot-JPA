package com.tatva.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tatva.pojo.User;

public interface UserDao extends JpaRepository<User, Long> {
	User findByEmailId(String emailId);
}
