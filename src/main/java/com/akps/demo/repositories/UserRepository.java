package com.akps.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;

import com.akps.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    @Query("SELECT u FROM User u WHERE TYPE(u) = :clazz")
    List<User> findByUserType(@Param("clazz") Class<? extends User> clazz);

	
}
