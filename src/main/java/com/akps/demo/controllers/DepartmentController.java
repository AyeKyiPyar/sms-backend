package com.akps.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.akps.demo.dtos.*;
import com.akps.demo.exceptions.RoleNotFoundException;
import com.akps.demo.exceptions.UserAlreadyExistsException;
import com.akps.demo.mapper.DepartmentMapper;
import com.akps.demo.services.DepartmentService;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")//@CrossOrigin(origins = "*")
public class DepartmentController
{
	@Autowired
	private DepartmentService departmentService;
	
	
	@GetMapping
	public List<DepartmentDTO> getAll()
	{
		return departmentService.findAll();
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody DepartmentDTO departmentDTO)
	{
		try 
    	{
         
			DepartmentDTO created = departmentService.createDepartment(departmentDTO);
			
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
	
	 // ===================== GET BY ID =====================
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) 
    {
        try 
        {
            DepartmentDTO department = departmentService.findById(id);
            return ResponseEntity.ok(department);

        } 
        catch (IllegalArgumentException e) 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));

        } 
        catch (Exception e) 
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", e.getMessage()));
        }
    }
	
 // ===================== UPDATE =====================
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody DepartmentDTO departmentDTO) {

        try 
        {
            DepartmentDTO updated = departmentService.updateDepartment(id, departmentDTO);
            return ResponseEntity.ok(updated);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    // ===================== DELETE =====================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) 
    {
        try 
        {
            departmentService.deleteDepartment(id);
            return ResponseEntity.ok(Map.of("message", "Department deleted successfully"));

        } 
        catch (IllegalArgumentException e) 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));

        } 
        catch (Exception e) 
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}
