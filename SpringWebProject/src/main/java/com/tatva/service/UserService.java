package com.tatva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import com.tatva.pojo.User;

public interface UserService {
	public void createUser(User user);
	public List<User> getUsers();
	public User getUserById(long id);
	public void updateUser(User user, long l);
	public void deleteUserById(long id);
	public User updatePartially(User user, long id);
	User findByEmailId(String emailId);
	boolean checkUser(String emailId, String password);
	String checkUser2(String emailId, String password);
	long checkUser3(String emailId, String password);
	User checkUser4(String emailId, String password);
}
