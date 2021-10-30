package com.keycloak.example;

import com.keycloak.example.entity.Employee;
import com.keycloak.example.service.EmployeeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/employees")
public class KeycloakApplication {

    @Autowired
    private EmployeeService employeeService;

    //User role
    @GetMapping("/{employeeId}")
    @RolesAllowed("user")
    public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId){
        return ResponseEntity.ok(employeeService.getEmployee(employeeId));
    }

    //Admin role
    @GetMapping
    @RolesAllowed("admin")
    public ResponseEntity<List<Employee>> findAllEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    public static void main(String[] args) {
        SpringApplication.run(KeycloakApplication.class, args);
    }


}
