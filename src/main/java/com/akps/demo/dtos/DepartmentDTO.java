package com.akps.demo.dtos;

import java.util.List;

import com.akps.demo.models.Teacher;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO 
{

 
    private Long id;


    private String name;

    private String description;


//    private List<Teacher> teachers;
}