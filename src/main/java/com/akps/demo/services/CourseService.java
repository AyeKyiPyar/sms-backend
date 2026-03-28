package com.akps.demo.services;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.akps.demo.dtos.CourseDTO;
import com.akps.demo.models.Course;

@Service
public interface CourseService 
{

	long count();

	List<CourseDTO> findAll();

	
	CourseDTO findById(Long id);

	CourseDTO createCourse(CourseDTO courseDTO);

	CourseDTO updateCourse(Long id, CourseDTO courseDTO);

	void deleteCourse(Long id);


}
