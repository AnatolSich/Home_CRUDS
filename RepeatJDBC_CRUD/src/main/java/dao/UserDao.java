package dao;

import model.User;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;

    public UserDao() {
        this.connection = DbUtil.getConnection();
    }

    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO users (firstname, lastname, email, dob) VALUES (?,?,?,?)");

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setDate(4, new Date(user.getDob().getTime()));

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User deleteUser(int userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement1 = connection
                    .prepareStatement("SELECT * FROM users WHERE id=?");
            preparedStatement1.setInt(1, userId);
            ResultSet resultSet = preparedStatement1.executeQuery();
            user.setId(resultSet.getInt(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setDob(resultSet.getDate(5));

            PreparedStatement preparedStatement2 = connection
                    .prepareStatement("DELETE FROM users WHERE id=?");
            preparedStatement2.setInt(1, userId);
            preparedStatement2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE users SET firstname = ?, lastname=?, email=?, dob=? WHERE id=?");
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setDate(4, new Date(user.getDob().getTime()));
            preparedStatement.setInt(5, user.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM users WHERE id=?");

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            user.setId(resultSet.getInt(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setDob(resultSet.getDate(5));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<User>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM users");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getDate(5));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
