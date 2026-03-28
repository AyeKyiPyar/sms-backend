package com.akps.demo.mapper;

import com.akps.demo.dtos.*;
import com.akps.demo.models.Department;

public class DepartmentMapper 
{
	public static DepartmentDTO mapToDTO(Department department)
	{
		DepartmentDTO dto = new DepartmentDTO();
		dto.setId(department.getId());
		dto.setName(department.getName());
		dto.setDescription(department.getDescription());
		return dto;
	}
	
	public static Department mapToEntity(DepartmentDTO departmentDTO)
	{
		if (departmentDTO == null) 
		{
	        return null;
	    }

	    Department department = new Department();
	    department.setId(departmentDTO.getId());
	    department.setName(departmentDTO.getName());
	    department.setDescription(departmentDTO.getDescription());

	    return department;
	}

}
