package com.example.demo.web;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/penalties", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)

public class PenaltiesApi {


    private final PenaltiesRestService penaltiesRestService;
    private final BorrowingRestService borrowingRestService;





    public PenaltiesApi(  PenaltiesRestService penaltiesRestService, BorrowingRestService borrowingRestService) {


        this.penaltiesRestService = penaltiesRestService;
        this.borrowingRestService =borrowingRestService;

    }

    @GetMapping
    public Page<Penalty> getAllPenalties(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                       @RequestParam(name = "page-size", defaultValue = "5", required = false) int size){
        return this.penaltiesRestService.getAllPenalties(page,size);

    }
    @GetMapping("/{Id}")
    public Optional<Penalty> getPenaltyById(@PathVariable("Id")Integer id){
        return this.penaltiesRestService.findPenaltyById(id);
    }
    @GetMapping("/pay/{Id}")
    public Penalty updatePenalty(@PathVariable("Id")Integer id){
        return this.penaltiesRestService.updatePenalty(id);
    }

    @DeleteMapping("/{Id}")
    public void deletePenalty(@PathVariable("Id")Integer id){
        this.penaltiesRestService.delete_Penalty(id);
    }
    @GetMapping("/dueDateToday")
    public List<Penalty> getWithDueDateToday(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        LocalDate date=LocalDate.now();
        System.out.println("=="+date);
        List<Penalty>penalties=this.penaltiesRestService.getAllPenalties();
        List<Penalty>today=new ArrayList<>();
        for(Penalty p : penalties){
            if(p.getDueDate().equals(date))
                today.add(p);
        }
        return today;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Penalty createPenalty( @RequestParam("Id") Integer Id, @RequestParam("givenAt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate givenAt, @RequestParam("dueDate")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate, @RequestParam("price")  Integer price){
        Borrowing borrowingAdd=this.borrowingRestService.findBorrowingById(Id).get();

        Penalty result=this.penaltiesRestService.createPenalty(new Penalty(borrowingAdd,givenAt,dueDate, price));
        //response.setHeader("Location", builder.path("/books/{id}").buildAndExpand(result.getName()).toUriString());
        return result;

    }
    @GetMapping("/search")
    public List<Penalty> search(@RequestParam("term") Integer term){

        return this.penaltiesRestService.searchPenalty(term);
    }

}
