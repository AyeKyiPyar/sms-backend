package com.akps.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akps.demo.dtos.StudentDTO;
import com.akps.demo.exceptions.RoleNotFoundException;
import com.akps.demo.exceptions.UserAlreadyExistsException;
import com.akps.demo.models.Student;
import com.akps.demo.repositories.StudentRepository;
import com.akps.demo.services.*;



@RestController
@RequestMapping("/api/students")
//@CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin(origins = "*")
public class StudentController 
{
	@Autowired
	private StudentService studentService;
	
	
	
	 // GET all students
    @GetMapping
    public List<StudentDTO> getAllStudents() 
    {
    	
        return studentService.getAllStudents();
    }
	
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody StudentDTO studentDTO)
	{
		
	
		try 
    	{
            // Call service to create user
           StudentDTO created = studentService.createStudent(studentDTO);
			
			 

            // 201 Created
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(created);

         
	    } 
    	catch (RoleNotFoundException e)
    	{
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(Map.of("message", e.getMessage()));
	
	    } 
    	catch (IllegalArgumentException e)
    	{
            // 400 Bad Request for invalid input
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));

        } 
    	catch (UserAlreadyExistsException e)
    	{
            // 409 Conflict if username/email already exists
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", e.getMessage()));

        } 
    	catch (Exception e) 
    	{
            // 500 Internal Server Error for any other exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", e.getMessage()));
        }
	}
	
	 // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable Long id) 
    {
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }
    
    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable Long id, @RequestBody StudentDTO studentDTO) 
    {
    	System.out.println("id *** " + id);
        StudentDTO updated = studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok(updated);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

	
	

}
