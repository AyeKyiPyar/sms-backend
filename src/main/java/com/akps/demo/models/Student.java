package com.akps.demo.models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("STUDENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@PrimaryKeyJoinColumn(name = "id")
@JsonIgnoreProperties("enrollments")
public class Student extends User 
{

    private String studentID;
    private String grade;
    
    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

  
}
