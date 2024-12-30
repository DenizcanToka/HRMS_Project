package com.webprogramlama.service;

import com.webprogramlama.common.GeneralException;
import com.webprogramlama.model.Department;
import com.webprogramlama.model.Employee;
import com.webprogramlama.repository.DepartmentRepository;
import com.webprogramlama.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(Integer id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setPhoneNumber(employeeDetails.getPhoneNumber());
            employee.setHireDate(employeeDetails.getHireDate());
            employee.setJobTitle(employeeDetails.getJobTitle());
            employee.setIdentityNo(employeeDetails.getIdentityNo());
            return employeeRepository.save(employee);
        }
        return null;
    }

    public void updateEmployee(Employee employee, Employee employeeDetails) {
        // Fetch and set the department if departmentId exists in employeeDetails
        if (employeeDetails.getDepartment() != null && employeeDetails.getDepartment().getId() != null) {
            Department department = departmentRepository.findById(employeeDetails.getDepartment().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid department ID: " + employeeDetails.getDepartment().getId()));
            employee.setDepartment(department);
        }

        // Update other fields from employeeDetails to employee
        if (employeeDetails.getFirstName() != null) {
            employee.setFirstName(employeeDetails.getFirstName());
        }

        if (employeeDetails.getLastName() != null) {
            employee.setLastName(employeeDetails.getLastName());
        }

        if (employeeDetails.getEmail() != null) {
            employee.setEmail(employeeDetails.getEmail());
        }

        if (employeeDetails.getPhoneNumber() != null) {
            employee.setPhoneNumber(employeeDetails.getPhoneNumber());
        }

        if (employeeDetails.getHireDate() != null) {
            employee.setHireDate(employeeDetails.getHireDate());
        }

        if (employeeDetails.getJobTitle() != null) {
            employee.setJobTitle(employeeDetails.getJobTitle());
        }

        if (employeeDetails.getIdentityNo() != null) {
            employee.setIdentityNo(employeeDetails.getIdentityNo());
        }
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
}


