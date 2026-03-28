package com.akps.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.akps.demo.dtos.CourseDTO;
import com.akps.demo.services.CourseService;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController
{
    @Autowired
    private CourseService courseService;

    // ================= GET ALL =================
    @GetMapping
    public List<CourseDTO> getAll()
    {
        return courseService.findAll();
    }

    // ================= FIND BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id)
    {
        try 
        {
            return ResponseEntity.ok(courseService.findById(id));
        } 
        catch (RuntimeException e) 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    // ================= CREATE =================
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CourseDTO courseDTO)
    {
        try 
        {
            CourseDTO created = courseService.createCourse(courseDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
            
        } 
        catch (IllegalArgumentException e) 
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", e.getMessage()));
        } 
        catch (Exception e) 
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody CourseDTO courseDTO)
    {
        try 
        {
            CourseDTO updated = courseService.updateCourse(id, courseDTO);
            return ResponseEntity.ok(updated);
        } 
        catch (RuntimeException e) 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok(Map.of("message", "Course deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}

