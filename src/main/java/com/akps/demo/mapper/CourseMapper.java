package com.akps.demo.mapper;

import com.akps.demo.dtos.CourseDTO;
import com.akps.demo.models.Course;

public class CourseMapper 
{

	// ================= ENTITY → DTO =================
    public static CourseDTO mapToDTO(Course course)
    {
        if (course == null)
        {
            return null;
        }

        return CourseDTO.builder()
                .id(course.getId())
                .courseCode(course.getCourseCode())
                .name(course.getName())
                .credit(course.getCredit())
                .build();
    }

    // ================= DTO → ENTITY =================
    public static Course mapToEntity(CourseDTO dto)
    {
        if (dto == null) 
        {
            return null;
        }

        return Course.builder()
        		.id(dto.getId())
        		.courseCode(dto.getCourseCode())
        		.name(dto.getName())
        		.credit(dto.getCredit())
        		.build();
    }

}
