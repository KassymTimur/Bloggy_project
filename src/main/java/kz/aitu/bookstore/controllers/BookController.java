package kz.aitu.bookstore.controllers;
import kz.aitu.bookstore.models.Book;
import kz.aitu.bookstore.services.interfaces.BookServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("books")
public class BookController {
    private final BookServiceInterface service;
    public BookController(BookServiceInterface service){
        this.service = service;
    }
    @GetMapping("/")
    public List<Book> getAllBooks(){
        return service.getAllBooks();
    }
    @GetMapping("/{book_id}")
    public ResponseEntity<Book> getById(@PathVariable("book_id") int book_id){
        Book book = service.getById(book_id);
        if (book == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<Book> create(@RequestBody Book book){
        Book createdBook = service.create(book);
        if(createdBook==null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/{book_id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("book_id") int book_id){
        service.deleteBook(book_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/title/{book_title}")
    public List<Book> getAllByTitle(@PathVariable("book_title") String title){
        return service.getByTitle(title);
    }
    @GetMapping("/author/{book_author}")
    public List<Book> getAllByAuthor(@PathVariable("book_author") String author){
        return service.getByAuthor(author);
    }
    @GetMapping("/isbn/{book_isbn}")
    public List<Book> getAllByIsbn(@PathVariable("book_isbn") String isbn){
        return service.getByIsbn(isbn);
    }
}
