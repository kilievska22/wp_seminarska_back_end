package com.example.demo.service.impl;

import com.example.demo.model.Book;
import com.example.demo.model.Employee;
import com.example.demo.repository.BooksJpaRepository;
import com.example.demo.repository.EmployeesJpaRepository;
import com.example.demo.service.BooksRestService;
import com.example.demo.service.EmployeesRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
class EmployeesRestServiceImpl  implements EmployeesRestService {
    private final EmployeesJpaRepository repository;


    public EmployeesRestServiceImpl(EmployeesJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Employee> getAllEmployees(int page, int size) {
        return this.repository.findAllByOrderByName(PageRequest.of(page, size

        ));
    }
    @Override
    public void delete_Employee(Integer id){
        this.repository.deleteById(id);

    }

    @Override
    public Employee editEmployee(Integer ESSN, String name,  Integer working_time, Integer salary, String position, String phone,
                                 String email) { ;
        this.repository.updateEmployee(ESSN, name, working_time,salary,position,phone, email);
        return this.repository.findById(ESSN).get();

    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    @Override
    public Optional<Employee> findEmployeeById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return this.repository.save(employee);
    }

    @Override
    public List<Employee> searchAllEmployees(String term) {
        if(isNumeric(term)==true){
         return   this.repository.searchEmployeeEssn(Integer.parseInt(term));}
        else {return this.repository.searchEmployee(term);}
    }




}
