package com.akps.demo.dtos;

import java.sql.Date;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentDTO
{

    private Long id;

 
    private StudentDTO student;



    private CourseDTO course;

  
    private Date createdAt;
    
   
    private String status; // "Active" or "Completed"

}
