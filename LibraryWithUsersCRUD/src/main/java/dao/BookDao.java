package dao;

import model.Book;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private Connection connection;

    public BookDao() {
        this.connection = DbUtil.getConnection();
    }

    public Book getBookById(int bookId) {
        Book book = new Book();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM books WHERE id=?");
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book.setId(resultSet.getInt(1));
                book.setName(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setRelease(resultSet.getDate(4));
                book.setQuantity(resultSet.getInt(5));
                book.setUserId(resultSet.getInt(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public List<Book> getAllBooksByUserId(int userId) {
        List<Book> list = new ArrayList<Book>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM books WHERE userId=?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getDate(4), resultSet.getInt(5), resultSet.getInt(6));
                list.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<Book>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM books");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getDate(4), resultSet.getInt(5), resultSet.getInt(6));
                list.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addBook(Book book) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO books(name, author, release, quantity, userid) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDate(3, new Date(book.getRelease().getTime()));
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setInt(5, book.getUserId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int bookId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM books WHERE id=?");
            preparedStatement.setInt(1, bookId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
