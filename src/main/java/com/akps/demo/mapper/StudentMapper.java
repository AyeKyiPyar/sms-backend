package com.akps.demo.mapper;

import com.akps.demo.dtos.StudentDTO;
import com.akps.demo.models.Student;

public class StudentMapper 
{
	public static Student mapToEntity(StudentDTO dto) 
	{
//        Student student = new Student();
//        student.setId(dto.getId());
//        student.setName(dto.getName());
//        student.setEmail(dto.getEmail());
//        student.setStudentID(dto.getStudentID());
//        student.setGrade(dto.getGrade());
//        return student;
		return Student.builder()
				.id(dto.getId())
				.name(dto.getName())
				.email(dto.getEmail())
				.password(dto.getPassword())
				.role(RoleMapper.mapToEntity(dto.getRole()))
				.studentID(dto.getStudentID())
				.grade(dto.getGrade())
				.build();
    }
	
	
	public static StudentDTO mapToDTO(Student student) 
	{
	    return StudentDTO.builder()
	    		.name(student.getName())
	    		.email(student.getEmail())
	    		.role(RoleMapper.mapToDTO(student.getRole()))
	    		.studentID(student.getStudentID())
	    		.grade(student.getGrade())
	    		.build();
	}
}
