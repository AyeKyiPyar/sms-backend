package com.akps.demo.controllers;

import lombok.RequiredArgsConstructor;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.akps.demo.dtos.*;
import com.akps.demo.exceptions.RoleNotFoundException;
import com.akps.demo.exceptions.UserAlreadyExistsException;

import com.akps.demo.models.*;
import com.akps.demo.services.*;
import com.akps.demo.services.impl.AdminServiceImpl;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController
{
	@Autowired
    private AdminService adminService;
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminDTO request) 
    {
    	RoleDTO role = new RoleDTO();
    	role.setName("ADMIN");
    	request.setRole(role);
    	System.out.println(request.getEmail() + " *********");
    	try
    	{
            // Call service
            UserDTO authUser = adminService.isAuthenticate(request);
            
            System.out.println("Admin login successfull!");
            // Success → 200 OK + user object
         
            return ResponseEntity.ok("Admin login successfull!");

        } 
    	catch (RuntimeException e) 
    	{
            String message = e.getMessage();

            // Determine status based on exception message
            HttpStatus status;
            if ("Invalid password".equals(message))
            {
                status = HttpStatus.UNAUTHORIZED; // 401
                System.out.println("******** 401 ******");
            } 
            else if ("Admin not found".equals(message)) 
            {
                status = HttpStatus.NOT_FOUND;    // 404
                System.out.println("******** 404 ******");
            } 
            else 
            {
                status = HttpStatus.BAD_REQUEST;  // 400 fallback
                System.out.println("******** 400 ******");
            }

            // Return message to frontend
            return ResponseEntity.status(status).body(message);
        }
    }
    	
   

}
