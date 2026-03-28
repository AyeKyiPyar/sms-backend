package com.akps.demo.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akps.demo.dtos.*;

import com.akps.demo.models.*;
import com.akps.demo.mapper.*;
import com.akps.demo.repositories.*;
import com.akps.demo.services.EnrollmentService;

@Service
public class EnrollmentServiceImpl implements EnrollmentService
{
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public long count()
	{
		
		return enrollmentRepository.count();
	}

	

	@Override
	public List<EnrollmentDTO> findAll() 
	{
		List<Enrollment> enrollments = enrollmentRepository.findAll();

	    if (enrollments == null || enrollments.isEmpty()) 
	    {
	        return new ArrayList<>(); // return empty list if no data
	    }

	    return enrollments.stream()
	            .map(enrollment -> {
	                EnrollmentDTO dto = EnrollmentMapper.mapToDTO(enrollment);
	                return dto != null ? dto : new EnrollmentDTO(); // avoid null DTO
	            })
	            .collect(Collectors.toList());
	}

	@Override
	public EnrollmentDTO findById(Long id) 
	{
		return EnrollmentMapper.mapToDTO(enrollmentRepository.findById(id).get());
		
	}

	@Override
	public EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO) 
	{
		
		
		 // 1️⃣ Fetch the Student entity from the database
	    Student student = studentRepository.findById(enrollmentDTO.getStudent().getId())
	            .orElseThrow(() -> new RuntimeException("Student not found with id: " 
	                                                   + enrollmentDTO.getStudent().getId()));

	    // 2️⃣ Fetch the Course entity from the database
	    Course course = courseRepository.findById(enrollmentDTO.getCourse().getId())
	            .orElseThrow(() -> new RuntimeException("Course not found with id: " 
	                                                   + enrollmentDTO.getCourse().getId()));

	    // 3️⃣ Convert DTO to Enrollment entity
	    Enrollment enrollment = new Enrollment();
	    enrollment.setStudent(student);
	    enrollment.setCourse(course);
	    enrollment.setStatus(enrollmentDTO.getStatus()); // e.g., Active, Completed, etc.

	    // 4️⃣ Save the entity
	    Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
		
		return EnrollmentMapper.mapToDTO(savedEnrollment);
	}
	
	
	@Override
	public EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO enrollmentDTO)
	{
		
	    Enrollment enrollment = enrollmentRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + id));
	   
	    Student student = studentRepository.findById(enrollmentDTO.getStudent().getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

	    Course course = courseRepository.findById(enrollmentDTO.getCourse().getId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
	    enrollment.setCreatedAt(enrollmentDTO.getCreatedAt());
	    enrollment.setStatus(enrollmentDTO.getStatus());
	    
	    enrollment.setStudent(student);
	    
	    enrollment.setCourse(course);

	    Enrollment updated = enrollmentRepository.save(enrollment);
	    
	    
	    return EnrollmentMapper.mapToDTO(updated);
	}
	
	
	@Override
	public void deleteEnrollment(Long id)
	{
	    if (!enrollmentRepository.existsById(id)) 
	    {
	        throw new RuntimeException("Enrollment not found with id: " + id);
	    }

	    enrollmentRepository.deleteById(id);
	}



	@Override
	public List<EnrollmentDTO> findTop5ByOrderByCreatedAtDesc()
	{
		
		return enrollmentRepository.findTop5ByOrderByCreatedAtDesc();
	}


}
