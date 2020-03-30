package com.example.demo.web;

import com.example.demo.model.*;
import com.example.demo.service.AuthorsRestService;
import com.example.demo.service.BooksRestService;
import com.example.demo.service.GenresRestService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/books", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)

public class BooksApi {

    private final BooksRestService booksRestService;
    private final GenresRestService genresRestService;
    private final AuthorsRestService authorsRestService;



    public BooksApi(BooksRestService booksRestService, GenresRestService genresRestService, AuthorsRestService authorsRestService) {

        this.booksRestService = booksRestService;
        this.genresRestService = genresRestService;

        this.authorsRestService = authorsRestService;
    }

    @GetMapping
    public Page<Book> getAllBooks(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                        @RequestParam(name = "page-size", defaultValue = "5", required = false) int size){
        return this.booksRestService.getAllBooks(page,size);

    }
    @GetMapping("/{Id}")
    public Optional<Book> getBookById(@PathVariable("Id")Integer id){
        return this.booksRestService.findBookById(id);
    }
    @DeleteMapping("/{Id}")
    public void deletePizza(@PathVariable("Id")Integer id){
        this.booksRestService.delete_Book(id);
    }
    @PatchMapping("/{Id}")
    public Book editBook(@PathVariable("Id") Integer id, @RequestParam("title") String title,
                                     @RequestParam("plot") String plot, @RequestParam("genre")Integer genre, @RequestParam("authors")List<String>authors, @RequestParam(value = "bookid", defaultValue = "0", required = false)Integer bookid){

        Genre genreAdd=this.genresRestService.findGenreById(genre).get();
        List<Author>authorsAdd=new ArrayList<>();
        for(String i:authors){
            System.out.println(i);
            Author find=this.authorsRestService.findAuthorById(Integer.parseInt(i)).get();
            authorsAdd.add(find);
        }

        return this.booksRestService.editBook(id, title, plot, genreAdd,authorsAdd);
    }
    @GetMapping("/search")
    public List<Book> search(@RequestParam("term") String term){

        return this.booksRestService.searchAllBooks(term);
    }
    @GetMapping("/getBorrowings")
    public List<CountBorrowings> search(){

        return this.booksRestService.getBorrowings();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestParam("title") String title, @RequestParam("plot") String plot, @RequestParam("genre") Integer genre,
@RequestParam("authors")  List<String>  authors,   HttpServletResponse response, UriComponentsBuilder builder){

        Genre genreAdd=this.genresRestService.findGenreById(genre).get();
List<Author>authorsAdd=new ArrayList<>();
for(String i:authors){
    System.out.println(i);
    Author find=this.authorsRestService.findAuthorById(Integer.parseInt(i)).get();
    authorsAdd.add(find);
}
        Book result=this.booksRestService.createBook(new Book(title,plot,genreAdd, authorsAdd));
        //response.setHeader("Location", builder.path("/books/{id}").buildAndExpand(result.getName()).toUriString());
        return result;

    }

}
