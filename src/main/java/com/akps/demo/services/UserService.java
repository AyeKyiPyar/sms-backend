package com.akps.demo.services;

import java.util.List;
import java.util.Optional;

import com.akps.demo.dtos.AdminDTO;
import com.akps.demo.dtos.UserDTO;
import com.akps.demo.models.User;

public interface UserService 
{
	public UserDTO isAuthenticate(AdminDTO request);
//	public UserDTO createUser(UserDTO dto, String roleName);
//	public List<UserDTO> findByUsername(String username);
}
