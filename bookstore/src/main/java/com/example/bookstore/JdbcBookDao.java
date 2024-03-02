package com.example.bookstore;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.sql.DataSource;
import com.example.bookstore.Book;
import com.example.bookstore.BookDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class JdbcBookDao implements BookDao {
    private static final Logger LOGGER = Logger.getLogger(JdbcBookDao.class.getName());
    private final DataSource dataSource;
    public JdbcBookDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM books")) {
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
            LOGGER.log(Level.SEVERE, "Error while getting all books", e);
            throw new RuntimeException(e);
        }
        return books;
    }
    @Override
    public Book getBookById(Integer id) {
        Book book = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    String isbn = resultSet.getString("isbn");
                    Integer shelfNumber = resultSet.getInt("shelfNumber");
                    book = new Book(id, title, author, isbn, shelfNumber);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while getting book by id", e);
            throw new RuntimeException(e);
        }
        return book;
    }
    @Override
    public Book getBookByIsbn(String isbn) {
        Book book = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE isbn = ?")) {
            statement.setString(1, isbn);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    Integer shelfNumber = resultSet.getInt("shelfNumber");
                    book = new Book(id, title, author, isbn, shelfNumber);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while getting book by isbn", e);
            throw new RuntimeException(e);
        }
        return book;
    }
    @Override
    public void updateBook(Book book) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE books SET title = ?, author = ?, isbn = ?, shelfNumber = ? WHERE id = ?")) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setInt(4, book.getShelfNumber());
            statement.setInt(5, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while updating book", e);
            throw new RuntimeException(e);
        }
    }
    @Override
    public void saveBook(Book book) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO books (id, title, author, isbn, shelfNumber) VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getIsbn());
            statement.setInt(5, book.getShelfNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while saving book", e);
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteBook(Integer id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while deleting book", e);
            throw new RuntimeException(e);
        }
    }
}
