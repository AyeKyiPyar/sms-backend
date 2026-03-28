package com.akps.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.akps.demo.dtos.TeacherDTO;


@Service
public interface TeacherService 
{

	long count();

	List<TeacherDTO> findAll();

	TeacherDTO findById(Long id);

    TeacherDTO create(TeacherDTO teacherDTO);

    TeacherDTO update(Long id, TeacherDTO teacherDTO);

    void delete(Long id);

}
