package com.akps.demo.mapper;

import com.akps.demo.dtos.TeacherDTO;
import com.akps.demo.models.Teacher;

public class TeacherMapper 
{
	public static TeacherDTO mapToDTO(Teacher teacher)
	{
		TeacherDTO dto = new TeacherDTO();
		dto.setId(teacher.getId());
		dto.setName(teacher.getName());
		dto.setEmail(teacher.getEmail());
		dto.setRole(RoleMapper.mapToDTO(teacher.getRole()));
		dto.setTeacherID(teacher.getTeacherID());
		dto.setDesignation(teacher.getDesignation());
		dto.setDepartment(teacher.getDepartment());
		
		return dto;
	}
	
	public static Teacher mapToEntity(TeacherDTO dto)
	{
		Teacher teacher = new Teacher();
        teacher.setName(dto.getName());
        teacher.setEmail(dto.getEmail());
        teacher.setTeacherID(dto.getTeacherID());
        teacher.setPassword(dto.getPassword());
        teacher.setRole(RoleMapper.mapToEntity(dto.getRole()));
        teacher.setDesignation(dto.getDesignation());
        teacher.setDepartment(dto.getDepartment());
        return teacher;
	}
}
