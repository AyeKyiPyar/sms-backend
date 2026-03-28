package com.akps.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.akps.demo.dtos.*;
import com.akps.demo.mapper.StudentMapper;
import com.akps.demo.models.Role;
import com.akps.demo.models.Student;
import com.akps.demo.repositories.RoleRepository;
import com.akps.demo.repositories.StudentRepository;
import com.akps.demo.services.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@CacheConfig(cacheNames = "students")
public class StudentServiceImpl implements StudentService
{
	@Autowired
    private StudentRepository studentRepository;
	
	@Autowired
	private RoleRepository roleRepository;
    

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "studentsList")
    public List<StudentDTO> getAllStudents()
    {
    	return studentRepository.findAll()
                .stream()
                .map(student -> StudentMapper.mapToDTO(student))
                .collect(Collectors.toList());
                
    }

    // ================= CREATE =================
    @Override
    @CachePut(key = "#result.id")   // store new student in cache
	public StudentDTO createStudent(StudentDTO studentDTO) 
	{
		//return StudentMapper.toStudentDTO(studentRepository.save(StudentMapper.toStudent(studentDTO)));
		 Role role = roleRepository.findByName("STUDENT")
                .orElseThrow(() -> new RuntimeException("Role not found"));
		
		 Student student = Student.builder()
			        .name(studentDTO.getName())
			        .email(studentDTO.getEmail())
			        .password(studentDTO.getPassword()) // encode
			        .studentID(studentDTO.getStudentID())
			        .grade(studentDTO.getGrade())
			        .role(role)
			        .build();


        Student saved = studentRepository.save(student);
       
        return StudentMapper.mapToDTO(saved);

	}

 // ================= COUNT =================
    @Override
    @Transactional(readOnly = true)
	public long count() 
	{
		
		return studentRepository.count();
	}

 // ================= GET BY ID =================
    @Override
    @Transactional(readOnly = true)
    @Cacheable(key = "#id")   // cache by id
	public StudentDTO getStudentById(Long id) 
	{
		Student student = studentRepository.findById(id).get();
		return StudentMapper.mapToDTO(student);
	}

    // ================= UPDATE =================
    @Override
    @CachePut(key = "#id")   // update cache
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) 
    {

        // 1️⃣ Find existing student
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));

        // 2️⃣ Update fields (ONLY editable fields)
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());

        // Update password only if provided
        if (studentDTO.getPassword() != null && !studentDTO.getPassword().isBlank()) 
        {
            student.setPassword(studentDTO.getPassword()); // encode if needed
        }

        student.setStudentID(studentDTO.getStudentID());
        student.setGrade(studentDTO.getGrade());
        
        System.out.println("student id ******" + student.getId());
        // 3️⃣ Save
        Student updated = studentRepository.save(student);
        System.out.println("********" + updated.getId());

        // 4️⃣ Convert Entity → DTO
        return StudentMapper.mapToDTO(updated);
    }

    // ================= DELETE =================
    @Override
    @CacheEvict(key = "#id")   // remove from cache
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));

        studentRepository.delete(student);
    }
}
