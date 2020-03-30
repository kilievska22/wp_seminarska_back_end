package com.example.demo.web;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Genre;
import com.example.demo.model.countBorrowingsAuthors;
import com.example.demo.service.AuthorsRestService;
import com.example.demo.service.BooksRestService;
import com.example.demo.service.GenresRestService;
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
@RequestMapping(path = "/authors", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)

public class AuthorsApi {

    private final AuthorsRestService authorsRestService;
    private final GenresRestService genresRestService;

    public AuthorsApi(AuthorsRestService authorsRestService, GenresRestService genresRestService) {

        this.authorsRestService = authorsRestService;
        this.genresRestService = genresRestService;
    }

    @GetMapping
    public Page<Author> getAllAuthors(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                  @RequestParam(name = "page-size", defaultValue = "5", required = false) int size){
        return this.authorsRestService.getAllAuthors(page,size);

    }
    @GetMapping("/search")
    public List<Author> search(@RequestParam("term") String term){

        return this.authorsRestService.searchAllAuthors(term);
    }
    @GetMapping("/{Id}")
    public Optional<Author> getAuthorById(@PathVariable("Id")Integer id){
        return this.authorsRestService.findAuthorById(id);
    }
    @DeleteMapping("/{Id}")
    public void deleteAuthor(@PathVariable("Id")Integer id){
        this.authorsRestService.delete_Author(id);
    }
    @PatchMapping("/{Id}")
    public Author editAuthor(@PathVariable("Id") Integer id, @RequestParam("name") String name,@RequestParam("date_of_birth")  String  date_of_birth,
                         @RequestParam("biography") String biography,@RequestParam("genre") Integer genre, @RequestParam(value = "authorId", defaultValue = "0", required = false) Integer authorId){


        Genre genreAdd=this.genresRestService.findGenreById(genre).get();

        return this.authorsRestService.editAuthor(id, name, date_of_birth, biography,genreAdd);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author createAuthor(@RequestParam("name") String name, @RequestParam("date_of_birth") String date_of_birth, @RequestParam("biography") String biography,
                               @RequestParam("genre") Integer genre,          HttpServletResponse response, UriComponentsBuilder builder){

        Genre genreAdd=this.genresRestService.findGenreById(genre).get();
        Author result=this.authorsRestService.createAuthor(new Author(name, date_of_birth,biography,genreAdd));
        //response.setHeader("Location", builder.path("/books/{id}").buildAndExpand(result.getName()).toUriString());
        return result;

    }
    @GetMapping("/getBorrowings")
    public List<countBorrowingsAuthors> search(){

        return this.authorsRestService.getBorrowings();
    }

}
