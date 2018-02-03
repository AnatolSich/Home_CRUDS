package dao;

import model.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {
    private Connection connection;

    public UsersDao() {
        this.connection = DBUtil.getConnection();
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM users");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getDate(3));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public User getUserById(int id) {
        User user = new User();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM users WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setDob(resultSet.getDate(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void createUser(User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO users (name, dob) VALUES (?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, new Date(user.getDob().getTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editUser(User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE users SET name=?, dob=? WHERE id=?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, new Date(user.getDob().getTime()));
            preparedStatement.setInt(3, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserById(int id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM users WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
