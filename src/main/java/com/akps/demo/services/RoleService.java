package com.akps.demo.services;

import java.util.List;

import com.akps.demo.models.Role;

public interface RoleService 
{
	 public Role getOrCreate(String roleName);
	 public Role findByName(String name);
	 List<Role> findByNames(List<String> names);
	 public List<Role> findAll();
	 
	 Role createRole(Role role);

	 Role getRoleById(Long id);
	 Role updateRole(Long id, Role role);
	 void deleteRole(Long id);

}
