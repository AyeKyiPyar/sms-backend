package com.akps.demo.dtos;

import com.akps.demo.models.Student;
import com.akps.demo.models.User;

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
public class StudentDTO extends UserDTO 
{

    private String studentID;
    private String grade;

  
}
