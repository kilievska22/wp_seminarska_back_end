package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.Employee;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeesRestService {
    public Page<Employee> getAllEmployees(int page, int size);
    public void delete_Employee(Integer id);
   public Employee editEmployee (Integer ESSN, String name,Integer working_time,Integer salary,String position,String phone,
                                  String email);
    public Optional<Employee>findEmployeeById(Integer id);
    public Employee createEmployee(Employee employee);
    List<Employee> searchAllEmployees(String term);



}
