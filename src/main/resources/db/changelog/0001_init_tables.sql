-- Departmanlar Tablosu (Bağımsız)
CREATE TABLE departments (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(255) NOT NULL UNIQUE,
                             location VARCHAR(255),
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Kullanıcılar Tablosu (Login/Register için)
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL DEFAULT 'employee', -- 'admin' veya 'employee'
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Çalışanlar Tablosu
CREATE TABLE employees (
                           id SERIAL PRIMARY KEY,
                           first_name VARCHAR(255) NOT NULL,
                           last_name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) NOT NULL UNIQUE,
                           phone_number VARCHAR(20),
                           hire_date DATE NOT NULL,
                           job_title VARCHAR(255) NOT NULL,
                           department_id INTEGER,
                           user_id INTEGER, -- Kullanıcı ile ilişkilendirme
                           CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES departments(id),
                           CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Projeler Tablosu
CREATE TABLE projects (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          start_date DATE NOT NULL,
                          end_date DATE,
                          department_id INTEGER NOT NULL,
                          CONSTRAINT fk_department_project FOREIGN KEY (department_id) REFERENCES departments(id)
);

-- Çalışan-Projeler İlişkisi (Bir çalışan birden fazla projede çalışabilir)
CREATE TABLE employee_projects (
                                   employee_id INTEGER NOT NULL,
                                   project_id INTEGER NOT NULL,
                                   PRIMARY KEY (employee_id, project_id),
                                   CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employees(id),
                                   CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES projects(id)
);
