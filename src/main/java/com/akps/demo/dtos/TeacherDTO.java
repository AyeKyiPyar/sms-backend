package com.akps.demo.dtos;

import com.akps.demo.models.Department;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TeacherDTO extends UserDTO
{
    
    private String teacherID;
    private String designation;
    
 
    private Department department;
    
}
