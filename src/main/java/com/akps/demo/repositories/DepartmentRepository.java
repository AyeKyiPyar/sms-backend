package com.akps.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akps.demo.models.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>
{

}
