package com.akps.demo.services;

import org.springframework.stereotype.Service;

import com.akps.demo.dtos.AdminDTO;


@Service
public interface AdminService 
{
	public AdminDTO isAuthenticate(AdminDTO request);
}
