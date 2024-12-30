package com.webprogramlama.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EmployeeProjectId implements Serializable {

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "project_id")
    private Integer projectId;
}
