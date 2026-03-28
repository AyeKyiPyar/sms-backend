package com.akps.demo.mapper;

import com.akps.demo.dtos.*;
import com.akps.demo.models.*;

public class UserMapper 
{
	public static Admin toAdminEntity(AdminDTO dto) 
	{
        Admin admin = new Admin();
        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        return admin;
    }

	public static AdminDTO toAdminDTO(Admin admin)
	{
	    return AdminDTO.builder()
//	            .id(admin.getId())
	            .name(admin.getName())
	            .email(admin.getEmail())
	            .adminLevel(admin.getAdminLevel())
	            .build();
	}


}
