package com.akps.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akps.demo.dtos.AdminDTO;

import com.akps.demo.mapper.UserMapper;
import com.akps.demo.models.Admin;
import com.akps.demo.repositories.AdminRepository;
import com.akps.demo.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	private AdminRepository adminRepository;

	@Override
    public AdminDTO isAuthenticate(AdminDTO request) 
    {
		
		  Admin requestAdmin = UserMapper.toAdminEntity(request);
		  System.out.println("service " + requestAdmin.getEmail());
		  
		  Admin dbAdmin = adminRepository.findByEmail(requestAdmin.getEmail())
		            .orElseThrow(() -> new RuntimeException("Admin not found"));

		    if (!dbAdmin.getPassword().equals(requestAdmin.getPassword())) 
		    {
		        throw new RuntimeException("Invalid password");
		    }

		    return UserMapper.toAdminDTO(dbAdmin);
		  
        
    }

	

}
