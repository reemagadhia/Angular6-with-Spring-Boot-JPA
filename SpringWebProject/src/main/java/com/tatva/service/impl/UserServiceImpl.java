package com.tatva.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tatva.dao.UserDao;
import com.tatva.pojo.User;
import com.tatva.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	public void createUser(User user) {
		// TODO Auto-generated method stub
		user.setRole("student");
		userDao.save(user);
		
	}
	
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return (List<User>) userDao.findAll();
	}

	
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return userDao.findById(id).orElse(null);
	}

	public void updateUser(User user, long l) {
		userDao.save(user);
	}
	
	public void deleteUserById(long id) {
		// TODO Auto-generated method stub
		userDao.deleteById(id);
	}
	
	public User updatePartially(User user, long id) {
        // TODO Auto-generated method stub
		User studentDummy = getUserById(id);
		studentDummy.setFullName(user.getFullName());
		studentDummy.setContact(user.getContact());
		studentDummy.setEmailId(user.getEmailId());
		studentDummy.setPassword(user.getPassword());
		return userDao.save(studentDummy);
    }
	public User findByEmailId(String emailId){
		User user = userDao.findByEmailId(emailId);
		return user;
	}
	
	@Override
	public boolean checkUser(String emailId, String password) {
		User user = findByEmailId(emailId);
		boolean test = false;
		if(emailId.equals(user.getEmailId()) && password.equals(user.getPassword())){
			test = true;
			return test;
		}else{
			return test;
		}
	}
	public String checkUser2(String emailId, String password) {
		User user = findByEmailId(emailId);
		boolean test = false;
		if(emailId.equals(user.getEmailId()) && password.equals(user.getPassword())){
			test = true;
			if(("admin").equals(user.getRole())){
				return "admin";
			}else if(("student").equals(user.getRole())){
				return "student";
			}else{
				return "";
			}
		}else{
			return "";
		}
	}
	public long checkUser3(String emailId, String password) {
		User user = findByEmailId(emailId);
		boolean test = false;
		if(emailId.equals(user.getEmailId()) && password.equals(user.getPassword())){
			test = true;
			return user.getuId();
		}else{
			return 0;
		}
	}
	public User checkUser4(String emailId, String password) {
		User user = findByEmailId(emailId);
		boolean test = false;
		if(emailId.equals(user.getEmailId()) && password.equals(user.getPassword())){
			test = true;
			return user;
		}else{
			return null;
		}
	}

}
