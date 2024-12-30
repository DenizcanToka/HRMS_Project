package com.webprogramlama.service;

import com.webprogramlama.model.Department;
import com.webprogramlama.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department updateDepartment(Integer id, Department department) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        existingDepartment.setName(department.getName());
        existingDepartment.setLocation(department.getLocation());
        return departmentRepository.save(existingDepartment);
    }


        public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }
}


