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

import com.tatva.pojo.User;
import com.tatva.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value={"/user"})
public class UserController{
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping(value="/create",headers="Accept=application/json") //headers="Accept=application/json" - to get data in json format
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder){
        System.out.println("Creating User "+user.getFullName());
        userService.createUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getuId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value="/getAll", headers="Accept=application/json")
    public List<User> getAllUsers() {
        List<User> user=userService.getUsers();
        return user;

    }

    @PutMapping(value="/update", headers="Accept=application/json")
    public ResponseEntity<String> updateStudent(@RequestBody User currentUser)
    {
        System.out.println("sd");
        User user = userService.getUserById(currentUser.getuId());
        if (user==null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        userService.updateUser(currentUser, currentUser.getuId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}", headers ="Accept=application/json")
    public ResponseEntity<User> deleteStudent(@PathVariable("id") long id){
    	User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
    
    @PatchMapping(value="/update/{id}", headers="Accept=application/json")
    public ResponseEntity<User> updateStudentPartially(@PathVariable("id") long id, @RequestBody User currentUser){
    	User user = userService.getUserById(id);
        if(user == null){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        User userUpdated = userService.updatePartially(currentUser, id);
        return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
    }
    
	@GetMapping(value = "/check/{emailId}/{password}")
    public ResponseEntity<User> checkStudent(@PathVariable("emailId") String emailId, @PathVariable("password") String password) {		
		User user = null;
		try{
			user = userService.checkUser4(emailId, password);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
		}catch(Exception e){
			return null;
		}
    }
}
    
    
//    @GetMapping(value="/check/{emailId}/{password}", headers="Accept=application/json")
//    public String checkPost(@PathVariable("emailId") String emailId, @PathVariable("password") String password)
//    {	boolean test;
//    	try{
//    		test = studentService.checkStudent(emailId, password);
//	    	if(test){
//	    		return "{\"goto\":1}";
//	    	}else{
//	    		return "{\"goto\":2}";
//	    	}
//    	}catch(Exception e){
//    		return "{\"goto\":2}";
//    	}
//    	
//    }
    
//    @GetMapping(value="/check/{emailId}/{password}", headers="Accept=application/json")
//    public String checkPost2(@PathVariable("emailId") String emailId, @PathVariable("password") String password)
//    {	String test;
//    	try{
//    		test = studentService.checkStudent2(emailId, password);
//    		System.out.println(test);
//	    	if(test.equals("admin")){
//	    		return "{\"goto\":1}";
//	    	}else if(test.equals("student")){
//	    		return "{\"goto\":2}";
//	    	}else{
//	    		return "{\"goto\":3}";
//	    	}
//    	}catch(Exception e){
//    		return "{\"goto\":3}";
//    	}
//    	
//    }
//    @GetMapping(value="/check/{emailId}/{password}", headers="Accept=application/json")
//    public String checkPost3(@PathVariable("emailId") String emailId, @PathVariable("password") String password)
//    {	long id;
//    	try{
//    		id = studentService.checkStudent3(emailId, password);
//    		Student student = studentService.getStudentById(id);
//	    	if(student.getRole().equals("admin")){
//	    		return "{\"goto\":1; \"id\":"+id+"}";
//	    	}else if(student.getRole().equals("student")){
//	    		return "{\"goto\":2; \"id\":"+id+"}";
//	    	}else{
//	    		return "{\"goto\":3}";
//	    	}
//    	}catch(Exception e){
//    		return "{\"goto\":3}";
//    	}
//    	
//    }
//	
//}