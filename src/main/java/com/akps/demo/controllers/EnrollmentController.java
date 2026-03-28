package com.akps.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import com.akps.demo.services.*;
import com.akps.demo.models.*;
import com.akps.demo.repositories.*;
import com.akps.demo.dtos.*;
import com.akps.demo.exceptions.RoleNotFoundException;
import com.akps.demo.exceptions.UserAlreadyExistsException;


@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController 
{

	@Autowired
	private EnrollmentService enrollmentService;
	
	// ✅ Get all enrollments
    @GetMapping
    public List<EnrollmentDTO> getAll() 
    {
    	List<EnrollmentDTO> enrollments = enrollmentService.findAll();

        return enrollments;
    }

    // ✅ Get enrollment by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) 
    {
        try 
        {
            EnrollmentDTO enrollment = enrollmentService.findById(id);
            return ResponseEntity.ok(enrollment);
            
        } 
        catch (RuntimeException e) 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    // ✅ Create enrollment
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EnrollmentDTO enrollmentDTO)
    {
    	System.out.println(enrollmentDTO);
        try 
        {
            EnrollmentDTO created = enrollmentService.createEnrollment(enrollmentDTO);
         // 201 Updated
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
            // 409 Conflict
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

    // ✅ Update enrollment
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EnrollmentDTO enrollmentDTO) 
    {
    	System.out.println(" controller " + enrollmentDTO.getStudent());
        try 
        {
            EnrollmentDTO updated = enrollmentService.updateEnrollment(id, enrollmentDTO);
         // 201 Updated
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(updated);

         
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
            // 409 Conflict
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

    // ✅ Delete enrollment
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) 
    {
        try 
        {
            enrollmentService.deleteEnrollment(id);
            return ResponseEntity.ok(Map.of("message", "Enrollment deleted successfully"));
            
        } 
        catch (RuntimeException e) 
        {
        	
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }
    }
	
	
	
}
