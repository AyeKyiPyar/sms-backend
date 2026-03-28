package com.akps.demo.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;




@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO 
{
    private Long id;
    private String name;
    private String email;
    private String password;
    
    private RoleDTO role; // role names
     
}

