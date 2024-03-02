package com.example.bookstore;
import java.util.logging.ErrorManager;
import java.util.logging.Logger;
import com.example.bookstore.Book;
import com.example.bookstore.BookDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class JdbcBookDao implements BookDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/books";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345";
//update
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM books";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                Integer shelfNumber = resultSet.getInt("shelfNumber");
                Book book = new Book(id, title, author, isbn, shelfNumber);
                books.add(book);
            }
        } catch (SQLException e) {
            ErrorManager logger = null;
            logger.error("Error while getting all books", e,1);
        }
        return books;
    }

    @Override
    public Book getBookById(Integer id) {
        Book book = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM books WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String isbn = resultSet.getString("isbn");
                Integer shelfNumber = resultSet.getInt("shelfNumber");
                book = new Book(id, title, author, isbn, shelfNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }


    @Override
    public void saveBook(Book book) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO books (id, title, author, isbn, shelfNumber) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getIsbn());
            statement.setInt(5, book.getShelfNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(Integer id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM books WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // other methods...
}
