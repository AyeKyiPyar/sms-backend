package com.akps.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.akps.demo.controllers.CourseController;
import com.akps.demo.models.Admin;
import com.akps.demo.models.Role;
import com.akps.demo.repositories.AdminRepository;
import com.akps.demo.repositories.RoleRepository;
import com.akps.demo.repositories.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner 
{

    private final CourseController courseController;

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private RoleRepository roleRepository;

    DataInitializer(CourseController courseController) {
        this.courseController = courseController;
    }

    @Override
    public void run(String... args) throws Exception 
    {

        // Check if admin already exists
        if (adminRepository.findByEmail("admin@example.com").isEmpty()) {

            // Create default admin
            Admin admin = new Admin();
            admin.setName("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword("admin123"); // You can encode it using BCryptPasswordEncoder
            Role role = new Role();
            role.setName("ADMIN");
            roleRepository.save(role);
            admin.setRole(role);
            
            admin.setAdminLevel("1");

           adminRepository.save(admin);

            System.out.println("Default admin user created!");
        }
    }
}
