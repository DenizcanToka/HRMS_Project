package com.webprogramlama.service;

import com.webprogramlama.model.EmployeeProject;
import com.webprogramlama.repository.EmployeeProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeProjectService {
    private final EmployeeProjectRepository employeeProjectRepository;

    @Autowired
    public EmployeeProjectService(EmployeeProjectRepository employeeProjectRepository) {
        this.employeeProjectRepository = employeeProjectRepository;
    }

    public List<EmployeeProject> getAllEmployeeProjects() {
        return employeeProjectRepository.findAll();
    }

    public EmployeeProject createEmployeeProject(EmployeeProject employeeProject) {
        return employeeProjectRepository.save(employeeProject);
    }

    public void deleteEmployeeProject(EmployeeProject employeeProject) {
        employeeProjectRepository.delete(employeeProject);
    }
}
