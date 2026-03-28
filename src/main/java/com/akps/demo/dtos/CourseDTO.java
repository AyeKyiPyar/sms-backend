package com.akps.demo.dtos;

import java.util.List;

import com.akps.demo.models.Enrollment;
import com.akps.demo.models.TeacherCourse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO 
{
	 private Long id;

	  
	 private String name;
	    
	 private String courseCode;
	    
	 private Integer credit;

	  
	 private List<Enrollment> enrollments;

}
