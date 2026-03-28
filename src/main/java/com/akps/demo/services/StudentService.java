package com.akps.demo.services;

import java.util.List;

import com.akps.demo.dtos.StudentDTO;

public interface StudentService
{
	List<StudentDTO> getAllStudents();

	StudentDTO createStudent(StudentDTO studentDTO);

	long count();

	StudentDTO getStudentById(Long id);

	StudentDTO updateStudent(Long id, StudentDTO studentDTO);

	void deleteStudent(Long id);
}
