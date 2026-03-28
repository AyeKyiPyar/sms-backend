package com.akps.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.akps.demo.dtos.TeacherDTO;
import com.akps.demo.services.TeacherService;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // ✅ READ ALL
    @GetMapping
    public List<TeacherDTO> getAll() 
    {
    	List<TeacherDTO> teachers = teacherService.findAll();
    	
        return teachers;
    }

    // ✅ READ BY ID (optional but recommended)
    @GetMapping("/{id}")
    public TeacherDTO getById(@PathVariable Long id)
    {
        return teacherService.findById(id);
    }

    // ✅ CREATE
    @PostMapping("/create")
    public ResponseEntity<TeacherDTO> create(@RequestBody TeacherDTO teacherDTO) {
        TeacherDTO savedTeacher = teacherService.create(teacherDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
    }

    // ✅ UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<TeacherDTO> update(
            @PathVariable Long id,
            @RequestBody TeacherDTO teacherDTO) 
    {

        TeacherDTO updatedTeacher = teacherService.update(id, teacherDTO);
        return ResponseEntity.ok(updatedTeacher);
    }

    // ✅ DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) 
    {
        teacherService.delete(id);
        return ResponseEntity.ok("Teacher deleted successfully");
    }
}

