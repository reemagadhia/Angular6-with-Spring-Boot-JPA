package com.tatva.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tatva.dao.StudentDao;
import com.tatva.pojo.Student;
import com.tatva.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentDao studentDao;
	
	public void createStudent(Student student) {
		// TODO Auto-generated method stub
		studentDao.save(student);
		
	}
	
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return (List<Student>) studentDao.findAll();
	}

	
	public Student getStudentById(long id) {
		// TODO Auto-generated method stub
		return studentDao.findById(id).orElse(null);
	}

	public void updateStudent(Student student, long l) {
		studentDao.save(student);
	}
	
	public void deleteStudentById(long id) {
		// TODO Auto-generated method stub
		studentDao.deleteById(id);
	}
	
	public Student updatePartially(Student student, long id) {
        // TODO Auto-generated method stub
		Student studentDummy = getStudentById(id);
		studentDummy.setFullName(student.getFullName());
		studentDummy.setContact(student.getContact());
		studentDummy.setEmailId(student.getEmailId());
		studentDummy.setPassword(student.getPassword());
		return studentDao.save(studentDummy);
    }
	public Student findByEmailId(String emailId){
		Student student = studentDao.findByEmailId(emailId);
		return student;
	}
	
	@Override
	public boolean checkStudent(String emailId, String password) {
		Student student = findByEmailId(emailId);
		boolean test = false;
		if(emailId.equals(student.getEmailId()) && password.equals(student.getPassword())){
			test = true;
			return test;
		}else{
			return test;
		}
	}

}
