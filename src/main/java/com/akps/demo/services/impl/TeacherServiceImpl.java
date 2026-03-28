package com.akps.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akps.demo.dtos.TeacherDTO;
import com.akps.demo.mapper.StudentMapper;
import com.akps.demo.mapper.TeacherMapper;
import com.akps.demo.models.Teacher;
import com.akps.demo.repositories.TeacherRepository;
import com.akps.demo.services.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService
{

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public long count()
	{
	
		return teacherRepository.count();
	}

	@Override
	public List<TeacherDTO> findAll() 
	{
	
		return teacherRepository.findAll()
		        .stream()
		        .map(TeacherMapper::mapToDTO) // use the mapper here
		        .collect(Collectors.toList());
	}

	// 🔹 FIND BY ID
    @Override
    public TeacherDTO findById(Long id) 
    {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Teacher not found with id: " + id));

        return TeacherMapper.mapToDTO(teacher);
    }

    // 🔹 CREATE
    @Override
    public TeacherDTO create(TeacherDTO teacherDTO) 
    {
        Teacher teacher = TeacherMapper.mapToEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return TeacherMapper.mapToDTO(savedTeacher);
    }

    // 🔹 UPDATE
    @Override
    public TeacherDTO update(Long id, TeacherDTO teacherDTO) 
    {

        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Teacher not found with id: " + id));

        // update fields
        existingTeacher.setName(teacherDTO.getName());
        existingTeacher.setEmail(teacherDTO.getEmail());
        existingTeacher.setPassword(teacherDTO.getPassword());
        existingTeacher.setDepartment(teacherDTO.getDepartment());

        Teacher updatedTeacher = teacherRepository.save(existingTeacher);
        return TeacherMapper.mapToDTO(updatedTeacher);
    }

    // 🔹 DELETE
    @Override
    public void delete(Long id) 
    {
        if (!teacherRepository.existsById(id)) 
        {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
        teacherRepository.deleteById(id);
    }

}
