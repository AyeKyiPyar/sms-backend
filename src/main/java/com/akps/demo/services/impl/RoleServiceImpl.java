package com.akps.demo.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.akps.demo.models.Role;
import com.akps.demo.repositories.RoleRepository;
import com.akps.demo.services.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService
{

    private final RoleRepository roleRepository;

    @Override
    public Role getOrCreate(String roleName) 
    {

        return roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(roleName);
                    return roleRepository.save(role);
                });
    }
    
    @Override
    public Role findByName(String name)
    {
        return roleRepository.findByName(name)
                .orElseThrow(() ->
                        new RuntimeException("Role not found: " + name));
    }

    @Override
    public List<Role> findAll()
    {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findByNames(List<String> names) {
        return names.stream()
                .map(this::findByName)
                .toList();
    }
    
    
    // ✅ CREATE
    @Override
    public Role createRole(Role role) 
    {
        // Optional: prevent duplicate role names
        if (roleRepository.findByName(role.getName()).isPresent()) {
            throw new RuntimeException("Role already exists: " + role.getName());
        }
        return roleRepository.save(role);
    }

    // ✅ READ BY ID
    @Override
    public Role getRoleById(Long id)
    {
        return roleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Role not found with id: " + id));
    }

    // ✅ UPDATE
    @Override
    public Role updateRole(Long id, Role role) 
    {
        Role existingRole = getRoleById(id);

        existingRole.setName(role.getName());

        return roleRepository.save(existingRole);
    }

    // ✅ DELETE
    @Override
    public void deleteRole(Long id)
    {
        Role role = getRoleById(id);
        roleRepository.delete(role);
    }
}
