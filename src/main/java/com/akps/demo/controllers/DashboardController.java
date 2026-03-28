package com.akps.demo.controllers;

import com.akps.demo.dtos.DashboardDTO;
import com.akps.demo.models.Enrollment;
import com.akps.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.akps.demo.services.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController 
{

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;
    
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @GetMapping
    public DashboardDTO getDashboardData() 
    {
    	System.out.println(" Dashboard  ");
        long totalStudents = studentService.count();
        long totalTeachers = teacherService.count();
        long totalCourses = courseService.count();
        long totalEnrollments = enrollmentService.count();
        
        System.out.println(totalStudents +  "  no. of students");
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // Fetch recent 5 enrollments
//        List<DashboardDTO.EnrollmentDTO> recentEnrollments = enrollmentService
//                .findTop5ByOrderByCreatedAtDesc()
//                .stream()
//                .map(e -> new DashboardDTO.EnrollmentDTO(
//                        e.getStudent() != null ? e.getStudent().getName() : "N/A",
//                        e.getCourse() != null ? e.getCourse().getName() : "N/A",
//                		e.getCreatedAt(),
//                        e.getStatus() != null ? e.getStatus() : "Unknown"
//                ))
//                .collect(Collectors.toList());

        List<DashboardDTO.EnrollmentDTO> recentEnrollments = new ArrayList<DashboardDTO.EnrollmentDTO>();

        return new DashboardDTO(totalStudents, totalTeachers, totalCourses, totalEnrollments, recentEnrollments);
    }
}