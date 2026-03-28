package com.akps.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akps.demo.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long>
{

}
