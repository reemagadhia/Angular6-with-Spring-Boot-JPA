package com.tatva.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tatva.pojo.Student;
import com.tatva.service.StudentService;

@RestController
@CrossOrigin(origins = "http://192.168.0.140:4200")
@RequestMapping(value={"/student"})
public class StudentController{
	
	@Autowired
	StudentService studentService;
	
	@GetMapping(value = "/get/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
        System.out.println("Fetching Student with id " + id);
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @PostMapping(value="/create",headers="Accept=application/json") //headers="Accept=application/json" - to get data in json format
    public ResponseEntity<Void> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder){
        System.out.println("Creating Student "+student.getFullName());
        studentService.createStudent(student);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/student/{id}").buildAndExpand(student.getsId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value="/getAll", headers="Accept=application/json")
    public List<Student> getAllStudent() {
        List<Student> student=studentService.getStudents();
        return student;

    }

    @PutMapping(value="/update", headers="Accept=application/json")
    public ResponseEntity<String> updateStudent(@RequestBody Student currentStudent)
    {
        System.out.println("sd");
        Student student = studentService.getStudentById(currentStudent.getsId());
        if (student==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        studentService.updateStudent(currentStudent, currentStudent.getsId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}", headers ="Accept=application/json")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") long id){
    	Student student = studentService.getStudentById(id);
        if (student == null) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        studentService.deleteStudentById(id);
        return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
    }
    
    @PatchMapping(value="/update/{id}", headers="Accept=application/json")
    public ResponseEntity<Student> updateStudentPartially(@PathVariable("id") long id, @RequestBody Student currentStudent){
    	Student student = studentService.getStudentById(id);
        if(student == null){
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
        Student studentUpdated = studentService.updatePartially(currentStudent, id);
        return new ResponseEntity<Student>(studentUpdated, HttpStatus.OK);
    }
    
    
    @GetMapping(value="/check/{emailId}/{password}", headers="Accept=application/json")
    public String checkPost(@PathVariable("emailId") String emailId, @PathVariable("password") String password)
    {	boolean test;
    	try{
    		test = studentService.checkStudent(emailId, password);
	    	if(test){
	    		return "{\"goto\":1}";
	    	}else{
	    		return "{\"goto\":2}";
	    	}
    	}catch(Exception e){
    		return "{\"goto\":2}";
    	}
    	
    }
	
}