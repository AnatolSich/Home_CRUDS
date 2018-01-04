package dao;

import model.Book;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoBook {

    private static Connection connection;

    public DaoBook() {
        connection = DbUtil.getConnection();
    }

    public  Book getBookById(int bookId) {
        Book book = new Book();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM books WHERE id=?");
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book.setId(resultSet.getInt(1));
                book.setName(resultSet.getString(2));
                book.setAuthor(resultSet.getString(3));
                book.setRelease(resultSet.getDate(4));
                book.setQuantity(resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public  List<Book> getALLBooks() {
        List<Book> list = new ArrayList<Book>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM books");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDate(4), resultSet.getInt(5));
                list.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public  void addBook(Book book) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO books (name, author, release, quantity) VALUES (?,?,?,?)");
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDate(3, new Date(book.getRelease().getTime()));
            preparedStatement.setInt(4, book.getQuantity());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void deleteBookById(int bookId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM books WHERE id=?");
            preparedStatement.setInt(1, bookId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void updateBook(Book book) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE books SET name=?,author=?,release=?,quantity=? WHERE id=?");
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDate(3, new Date(book.getRelease().getTime()));
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setInt(5, book.getId()); //нужный id обеспечивается в BookController.java

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
