package com.keycloak.example.service;

import com.keycloak.example.entity.Employee;
import com.keycloak.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostConstruct
    public void initializeEmployeeTable(){
        employeeRepository.saveAll(
                Stream.of(
                        new Employee("John", 2000),
                        new Employee("Doe", 2020),
                        new Employee("Maggie", 2021)
                ).collect(Collectors.toList())
        );
    }

    public Employee getEmployee(int employeeId){
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }
}
