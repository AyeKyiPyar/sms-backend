package com.akps.demo.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.akps.demo.dtos.DepartmentDTO;
import com.akps.demo.models.Department;

@Service
public interface DepartmentService
{

	List<DepartmentDTO> findAll();

	long count();

	DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

	void deleteDepartment(Long id);

	DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO);

	DepartmentDTO findById(Long id);

}
