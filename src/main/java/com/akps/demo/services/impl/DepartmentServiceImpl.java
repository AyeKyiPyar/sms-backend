package com.akps.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akps.demo.dtos.DepartmentDTO;
import com.akps.demo.dtos.TeacherDTO;
import com.akps.demo.mapper.DepartmentMapper;
import com.akps.demo.mapper.TeacherMapper;
import com.akps.demo.models.Department;
import com.akps.demo.repositories.DepartmentRepository;
import com.akps.demo.services.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService
{

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public long count()
	{
	
		return departmentRepository.count();
	}

	@Override
	public List<DepartmentDTO> findAll() 
	{
	
		return departmentRepository.findAll()
				.stream()
				.map(department -> DepartmentMapper.mapToDTO(department))
				.collect(Collectors.toList());
	}

	@Override
	public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) 
	{
		 if (departmentDTO == null) 
		 {
	            throw new IllegalArgumentException("Department data must not be null");
	        }

	        // Map DTO → Entity
	        Department department = DepartmentMapper.mapToEntity(departmentDTO);

	        // Save
	        Department savedDepartment = departmentRepository.save(department);

	        // Map Entity → DTO
	        return DepartmentMapper.mapToDTO(savedDepartment);
	}

	// ===================== DELETE =====================
    @Override
    public void deleteDepartment(Long id)
    {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + id));

        departmentRepository.delete(department);
    }

    @Override
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) 
    {

        if (departmentDTO == null) 
        {
            throw new IllegalArgumentException("Department data must not be null");
        }

        // 1️⃣ Find existing department
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Department not found with id: " + id)
                );

        // 2️⃣ Update fields (only editable ones)
        existing.setName(departmentDTO.getName());
        existing.setDescription(departmentDTO.getDescription());

        // 3️⃣ Save updated entity
        Department updated = departmentRepository.save(existing);

        // 4️⃣ Convert back to DTO
        return DepartmentMapper.mapToDTO(updated);
    }

	@Override
	public DepartmentDTO findById(Long id) 
	{
		Department department = departmentRepository.findById(id).get();
		return DepartmentMapper.mapToDTO(department);
	}
	
	
}
