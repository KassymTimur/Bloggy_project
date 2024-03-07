package kz.aitu.bookstore.services;
import kz.aitu.bookstore.models.Book;
import kz.aitu.bookstore.repositories.BookRepositoryInterface;
import kz.aitu.bookstore.services.interfaces.BookServiceInterface;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class BookService implements BookServiceInterface{
    private final BookRepositoryInterface repo;
    public BookService(BookRepositoryInterface repo) {this.repo = repo;}
    @Override
    public List<Book> getAllBooks() {return repo.findAll();}
    @Override
    public Book getById(int book_id){return repo.findById(book_id).orElse(null);}
    @Override
    public Book create(Book book){
        return repo.save(book);
    }
    @Override
    public void deleteBook(int book_id){
        repo.deleteById(book_id);
    }
    @Override
    public List<Book> getByTitle(String title){
        return repo.findByTitle(title);
    }
    @Override
    public List<Book> getByAuthor(String author){
       return repo.findByAuthor(author);
    }
    @Override
    public List<Book> getByIsbn(String isbn){
        return repo.findByIsbn(isbn);
    }
}
