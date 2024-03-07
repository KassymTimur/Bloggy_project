package kz.aitu.bookstore.services.interfaces;
import kz.aitu.bookstore.models.Book;
import java.util.List;
public interface BookServiceInterface {
    List<Book> getAllBooks();
    Book getById(int book_id);
    Book create(Book book);
    void deleteBook(int book_id);
    List<Book> getByTitle(String title);
    List<Book> getByAuthor(String author);
    List<Book> getByIsbn(String isbn);
}
