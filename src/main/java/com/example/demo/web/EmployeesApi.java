package com.example.demo.web;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Employee;
import com.example.demo.service.AuthorsRestService;
import com.example.demo.service.BooksRestService;
import com.example.demo.service.EmployeesRestService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/employees", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)

public class EmployeesApi {

    private final EmployeesRestService employeesRestService;

    public EmployeesApi(EmployeesRestService authorsRestService) {

        this.employeesRestService = authorsRestService;
    }

    @GetMapping
    public Page<Employee> getAllEmployees(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                          @RequestParam(name = "page-size", defaultValue = "5", required = false) int size){
        return this.employeesRestService.getAllEmployees(page,size);

    }
    @GetMapping("/{Id}")
    public Optional<Employee> getEmployeeById(@PathVariable("Id")Integer id){
        return this.employeesRestService.findEmployeeById(id);
    }
    @DeleteMapping("/{Id}")
    public void deleteEmployee(@PathVariable("Id")Integer id){
        this.employeesRestService.delete_Employee(id);
    }
    @PatchMapping("/{Id}")
    public Employee editEmployee(@PathVariable("Id") Integer id, @RequestParam("name") String name,
                         @RequestParam("working_time")Integer working_time,  @RequestParam("salary")Integer salary,   @RequestParam("position") String position,@RequestParam("phone") String phone,@RequestParam("email") String email, @RequestParam(value = "ESSN", defaultValue = "0", required = false) Integer ESSN){



        return this.employeesRestService.editEmployee(id, name, working_time,salary,position,phone,email);
    }
    @GetMapping("/search")
    public List<Employee> search(@RequestParam("term") String term){

        return this.employeesRestService.searchAllEmployees(term);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestParam("name") String name,
                               @RequestParam("working_time")Integer working_time,  @RequestParam("salary")Integer salary,   @RequestParam("position") String position,@RequestParam("phone") String phone,@RequestParam("email") String email, @RequestParam( "ESSN") Integer ESSN){



        Employee result=this.employeesRestService.createEmployee(new Employee (ESSN, name,working_time,salary,position,phone,email));
        //response.setHeader("Location", builder.path("/books/{id}").buildAndExpand(result.getName()).toUriString());
        return result;

    }

}
