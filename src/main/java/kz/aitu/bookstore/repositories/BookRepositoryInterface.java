package kz.aitu.bookstore.repositories;
import kz.aitu.bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BookRepositoryInterface extends JpaRepository<Book, Integer>{
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByIsbn(String isbn);

}
