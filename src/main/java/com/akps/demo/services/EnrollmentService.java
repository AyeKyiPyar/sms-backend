package com.akps.demo.services;

import java.util.*;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;


import com.akps.demo.dtos.*;
import com.akps.demo.models.Enrollment;

@Service
public interface EnrollmentService 
{

	long count();

	List<EnrollmentDTO> findTop5ByOrderByCreatedAtDesc();

	List<EnrollmentDTO> findAll();

	EnrollmentDTO findById(Long id);

	EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO);

	EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO enrollmentDTO);

	void deleteEnrollment(Long id);



	

}
