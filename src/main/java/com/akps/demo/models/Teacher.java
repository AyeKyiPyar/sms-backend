package com.akps.demo.models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@PrimaryKeyJoinColumn(name = "id")
public class Teacher extends User 
{

    private String teacherID;
    private String designation;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore // ignore department when serializing
    private Department department;
    
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<TeacherCourse> courses;
  
}
