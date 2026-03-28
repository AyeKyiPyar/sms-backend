package com.akps.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akps.demo.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>
{
	Optional<Admin> findByEmail(String email);


}
