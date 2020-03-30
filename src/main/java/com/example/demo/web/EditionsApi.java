package com.example.demo.web;

import com.example.demo.model.Book;
import com.example.demo.model.Edition;
import com.example.demo.service.BooksRestService;
import com.example.demo.service.EditionsRestService;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/editions", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)

public class EditionsApi {
    private final BooksRestService booksRestService;
    private final EditionsRestService editionsRestService;
    public EditionsApi(EditionsRestService editionsRestService, BooksRestService booksRestService) {

        this.booksRestService = booksRestService;
        this.editionsRestService = editionsRestService;

    }
    @GetMapping
    public List<Edition> getAllEditions(){
        return this.editionsRestService.getAllEditions();

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Edition createEditon(@RequestParam("Id") Integer Id, @RequestParam("num_pages") Integer num_pages,
                                HttpServletResponse response, UriComponentsBuilder builder){

        Book book=this.booksRestService.findBookById(Id).get();

        Edition result=this.editionsRestService.createEdition(new Edition(num_pages,book,true));
        //response.setHeader("Location", builder.path("/books/{id}").buildAndExpand(result.getName()).toUriString());
        return result;

    }





}
