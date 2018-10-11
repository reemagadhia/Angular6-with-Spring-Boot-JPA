package com.tatva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import com.tatva.pojo.Student;

public interface StudentService {
	public void createStudent(Student student);
	public List<Student> getStudents();
	public Student getStudentById(long id);
	public void updateStudent(Student student, long l);
	public void deleteStudentById(long id);
	public Student updatePartially(Student student, long id);
	Student findByEmailId(String emailId);
	boolean checkStudent(String emailId, String password);
}
