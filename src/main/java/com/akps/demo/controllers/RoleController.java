package com.akps.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.akps.demo.services.RoleService;
import com.akps.demo.models.*;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleController 
{

    private final RoleService roleService;

    public RoleController(RoleService roleService) 
    {
        this.roleService = roleService;
    }

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role)
    {
        return ResponseEntity.ok(roleService.createRole(role));
    }

    // ✅ READ ALL
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() 
    {
        return ResponseEntity.ok(roleService.findAll());
    }

    // ✅ READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) 
    {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id,@RequestBody Role role) 
    {
        return ResponseEntity.ok(roleService.updateRole(id, role));
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) 
    {
        roleService.deleteRole(id);
        return ResponseEntity.ok("Role deleted successfully");
    }
}

