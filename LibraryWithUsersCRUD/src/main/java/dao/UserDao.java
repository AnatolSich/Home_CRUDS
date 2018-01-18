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

    public User getUserById(Integer userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM users WHERE id=?");

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while ((resultSet.next())) {
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setDob(resultSet.getDate(3));
                user.setAddress(resultSet.getString(4));
                user.setEmail(resultSet.getString(5));
                user.setTel(resultSet.getString(6));
            }
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
                User newUser = new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6));
                list.add(newUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addUser(User newUser) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO users (name, dob, address, email, tel) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, newUser.getName());
            preparedStatement.setDate(2, new Date(newUser.getDob().getTime()));
            preparedStatement.setString(3, newUser.getAddress());
            preparedStatement.setString(4, newUser.getEmail());
            preparedStatement.setString(5, newUser.getTel());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM users WHERE id=?");
            preparedStatement.setInt(1, userId);
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editUser(User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE users SET name=?,dob=?,address=?, email=?, tel=? WHERE id=?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, new Date(user.getDob().getTime()));
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getTel());
            preparedStatement.setInt(6, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
