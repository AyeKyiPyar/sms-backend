package com.akps.demo.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
//@DiscriminatorValue("ADMIN")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class Admin extends User
{
	private String adminLevel;
}
