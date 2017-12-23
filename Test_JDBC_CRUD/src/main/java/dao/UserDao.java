package dao;

import model.User;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;

    public UserDao() {
        connection = DbUtil.getConnection();
    }

    public void addUser(User user) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO users(firstname, lastname, email, dob) VALUES (?,?,?,?)");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setDate(4, new Date(user.getDob().getTime()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("DELETE FROM users WHERE id=?");
            ps.setInt(1, userId);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            PreparedStatement ps = connection
                    .prepareStatement("UPDATE users SET firstname=?, lastname=?, dob=?, email=? WHERE id=?");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setDate(3, new Date(user.getDob().getTime()));
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List getAllUsers() {
        List<User> userList = new ArrayList<User>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getDate(5));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
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
}
