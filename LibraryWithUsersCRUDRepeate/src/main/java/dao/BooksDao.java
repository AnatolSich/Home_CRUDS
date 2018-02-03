package dao;

import model.Book;
import model.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksDao {
    private Connection connection;

    public BooksDao() {
        this.connection = DBUtil.getConnection();
    }

    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM books");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getDate(3), resultSet.getInt(4));
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Book> getBooksByUserId(int userId) {
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM books WHERE userid=?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getDate(3), resultSet.getInt(4));
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Book getBookById(int id) {
        Book book = new Book();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM books WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book.setId(resultSet.getInt(1));
                book.setTitle(resultSet.getString(2));
                book.setReleaseDate(resultSet.getDate(3));
                book.setUserId(resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public void createBook(Book book) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO books (title, releasedate, userid) VALUES (?,?,?)");
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setDate(2, new Date(book.getReleaseDate().getTime()));
            preparedStatement.setInt(3, book.getUserId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editBook(Book book) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE books SET title=?, releasedate=?,userid=? WHERE id=?");
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setDate(2, new Date(book.getReleaseDate().getTime()));
            preparedStatement.setInt(3, book.getUserId());
            preparedStatement.setInt(4, book.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBookById(int id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM books WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
