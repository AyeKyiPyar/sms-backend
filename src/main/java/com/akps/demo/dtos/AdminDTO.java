package com.akps.demo.dtos;

import java.util.List;

import com.akps.demo.models.Role;

import lombok.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO extends UserDTO
{
	private String adminLevel;

}
