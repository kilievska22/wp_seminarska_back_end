package com.example.demo.web;

import com.example.demo.model.Book;
import com.example.demo.model.CountBorrowings;
import com.example.demo.model.Genre;
import com.example.demo.model.countBorrowingsGenres;
import com.example.demo.service.BooksRestService;
import com.example.demo.service.GenresRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/genres", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)

public class GenresApi {

    private final GenresRestService genresRestService;

    public GenresApi(GenresRestService genresRestService) {

        this.genresRestService = genresRestService;
    }

    @GetMapping
    public Page<Genre> getAllGenres(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                   @RequestParam(name = "page-size", defaultValue = "5", required = false) int size){
        return this.genresRestService.getAllGenres(page,size);

    }
    @GetMapping("/{Id}")
    public Optional<Genre> getGenreById(@PathVariable("Id")Integer id){
        return this.genresRestService.findGenreById(id);
    }
    @DeleteMapping("/{Id}")
    public void deleteGenre(@PathVariable("Id")Integer id){
        this.genresRestService.delete_Genre(id);
    }
    @PatchMapping("/{Id}")
    public Genre editGenre(@PathVariable("Id") Integer id, @RequestParam("title") String title,
                         @RequestParam("description") String description, @RequestParam("period")Long period, @RequestParam(value = "genre_id", defaultValue = "0", required = false)Integer genreid){



        return this.genresRestService.editGenre(id, title, description, period);
    }

    @GetMapping("/search")
    public List<Genre> search(@RequestParam("term") String term){

    return this.genresRestService.searchAllGenres(term);
}




    @GetMapping("/getBorrowings")
    public List<countBorrowingsGenres> search(){

        return this.genresRestService.getBorrowings();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Genre createGenre(@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("period")Long period,
                           HttpServletResponse response, UriComponentsBuilder builder){


        Genre result=this.genresRestService.createGenre(new Genre(title,description,period));
        //response.setHeader("Location", builder.path("/books/{id}").buildAndExpand(result.getName()).toUriString());
        return result;

    }

}
