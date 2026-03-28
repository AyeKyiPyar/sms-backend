package com.akps.demo.mapper;

import com.akps.demo.dtos.RoleDTO;
import com.akps.demo.models.Role;

public class RoleMapper 
{
	public static Role mapToEntity(RoleDTO dto)
	{
		return Role.builder()
				.id(dto.getId())
				.name(dto.getName())
				.build();
	}
	
	public static RoleDTO mapToDTO(Role role)
	{
		return RoleDTO.builder()
				.id(role.getId())
				.name(role.getName())
				.build();
	}
}
