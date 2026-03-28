package com.akps.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akps.demo.dtos.CourseDTO;
import com.akps.demo.mapper.CourseMapper;
import com.akps.demo.mapper.StudentMapper;
import com.akps.demo.models.Course;
import com.akps.demo.repositories.CourseRepository;
import com.akps.demo.repositories.UserRepository;
import com.akps.demo.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService
{

    private final UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

    CourseServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@Override
	public long count() 
	{
		
		return courseRepository.count();
	}

	@Override
	public List<CourseDTO> findAll() 
	{
		return courseRepository.findAll()
                .stream()
                .map(course -> CourseMapper.mapToDTO(course))
                .collect(Collectors.toList());
                
	}

	@Override
	public CourseDTO findById(Long id)
	{
		// TODO Auto-generated method stub
		return CourseMapper.mapToDTO(courseRepository.findById(id).get());
	}

	// ================= CREATE =================
    @Override
    public CourseDTO createCourse(CourseDTO courseDTO)
    {
        // DTO → Entity
        Course course = CourseMapper.mapToEntity(courseDTO);

        // Ensure ID is null for create
        course.setId(null);

        Course saved = courseRepository.save(course);

        // Entity → DTO
        return CourseMapper.mapToDTO(saved);
    }

    // ================= UPDATE =================
    @Override
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO)
    {
        Course existing = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        // Update fields
        existing.setCourseCode(courseDTO.getCourseCode());
        existing.setName(courseDTO.getName());
        existing.setCredit(courseDTO.getCredit());

        Course updated = courseRepository.save(existing);

        return CourseMapper.mapToDTO(updated);
    }

    // ================= DELETE =================
    @Override
    public void deleteCourse(Long id)
    {
        Course existing = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        courseRepository.delete(existing);
    }

}
