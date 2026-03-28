package com.akps.demo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akps.demo.dtos.EnrollmentDTO;
import com.akps.demo.models.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>
{

	List<EnrollmentDTO> findTop5ByOrderByCreatedAtDesc();
	 
}
