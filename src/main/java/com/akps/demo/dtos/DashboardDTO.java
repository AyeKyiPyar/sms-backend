// src/main/java/com/example/demo/dto/DashboardDTO.java
package com.akps.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO 
{

    private long totalStudents;
    private long totalTeachers;
    private long totalCourses;
    private long totalEnrollments;
    private List<EnrollmentDTO> recentEnrollments;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EnrollmentDTO 
    {
        private String studentName;
        private String courseName;
      
        private Date createdAt; 
 
        private String status;
    }
}
