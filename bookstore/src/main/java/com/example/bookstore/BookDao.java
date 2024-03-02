package com.example.bookstore;
import java.util.List;
public interface BookDao {
    List<Book> getAllBooks();
    Book getBookById(Integer id);
    Book getBookByIsbn(String isbn);
    void saveBook(Book book);
    void updateBook(Book book);
    void deleteBook(Integer id);
}