package com.tatva.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tatva.pojo.Student;

public interface StudentDao extends JpaRepository<Student, Long> {
	Student findByEmailId(String emailId);
}
