package com.akps.demo.models;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment 
{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@JsonManagedReference
	@ManyToOne(fetch = FetchType.EAGER)
    private Student student;

	@JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    @CreationTimestamp
    private Date createdAt;
   
    private String status; // "Active" or "Completed"

}
