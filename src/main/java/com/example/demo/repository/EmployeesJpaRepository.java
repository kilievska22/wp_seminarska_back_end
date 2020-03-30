package com.example.demo.repository;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface EmployeesJpaRepository extends JpaRepository<Employee, Integer> {
    Page<Employee> findAllByOrderByName(Pageable pageable);
    @Modifying
    @Transactional
    @Query("UPDATE Employee e set e.name=:name,  e.working_time=:working_time, e.salary=:salary, e.position=:position,e.phone=:phone, e.email=:email where e.ESSN=:ESSN")
    void updateEmployee(@Param("ESSN")Integer ESSN, @Param("name")String name,
                      @Param("working_time")Integer working_time, @Param("salary")Integer salary, @Param("position")String position, @Param("phone")String phone, @Param("email")String email);
    @Query("select e from Employee e WHERE e.name like :term ")
    List<Employee> searchEmployee(@Param("term") String term);
    @Query("select e from Employee e  WHERE e.ESSN like :parseInt ")
    List<Employee> searchEmployeeEssn(Integer parseInt);

}

