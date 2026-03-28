package com.akps.demo.mapper;

import com.akps.demo.dtos.EnrollmentDTO;
import com.akps.demo.models.Enrollment;

public class EnrollmentMapper 
{
	// =========================
    // Entity ➜ DTO
    // =========================
//    public static EnrollmentDTO mapToDTO(Enrollment enrollment) 
//    {
//        if (enrollment == null) return null;
//
//        return EnrollmentDTO.builder()
//                .id(enrollment.getId())
//                .student(enrollment.getStudent())
//                .course(enrollment.getCourse())
//                .createdAt(enrollment.getCreatedAt())
//                .status(enrollment.getStatus())
//                .build();
//    }

    // =========================
    // DTO ➜ Entity
    // =========================
//    public static Enrollment mapToEntity(EnrollmentDTO enrollmentDTO) 
//    {
//        if (enrollmentDTO == null) return null;
//
//        Enrollment enrollment = new Enrollment();
//        enrollment.setId(enrollmentDTO.getId());
//        enrollment.setStudent(enrollmentDTO.getStudent());
//        enrollment.setCourse(enrollmentDTO.getCourse());
//        enrollment.setCreatedAt(enrollmentDTO.getCreatedAt());
//        enrollment.setStatus(enrollmentDTO.getStatus());
//
//        return enrollment;
//    }
	
	 public static EnrollmentDTO mapToDTO(Enrollment enrollment) 
	 {
	        if (enrollment == null) return null;

	        EnrollmentDTO dto = new EnrollmentDTO();
	        dto.setId(enrollment.getId());
	        dto.setStatus(enrollment.getStatus());
	        dto.setCreatedAt(enrollment.getCreatedAt());

	        // Safe mapping for nested objects
	        if (enrollment.getStudent() != null) 
	        {
	            dto.setStudent(StudentMapper.mapToDTO(enrollment.getStudent())); // or map to StudentDTO if needed
	        }

	        if (enrollment.getCourse() != null) 
	        {
	            dto.setCourse(CourseMapper.mapToDTO(enrollment.getCourse())); // or map to CourseDTO if needed
	        }

	        return dto;
	    }

	    public static Enrollment mapToEntity(EnrollmentDTO dto) 
	    {
	        if (dto == null) return null;

	        Enrollment enrollment = new Enrollment();
	        enrollment.setId(dto.getId());
	        enrollment.setStatus(dto.getStatus());
	        enrollment.setCreatedAt(dto.getCreatedAt());

	        if (dto.getStudent() != null) 
	        {
	            enrollment.setStudent(StudentMapper.mapToEntity(dto.getStudent())); // or convert from DTO
	        }

	        if (dto.getCourse() != null) 
	        {
	            enrollment.setCourse(CourseMapper.mapToEntity(dto.getCourse())); // or convert from DTO
	        }

	        return enrollment;
	    }
}
