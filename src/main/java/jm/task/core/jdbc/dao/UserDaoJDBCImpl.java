package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final Util util = new Util();

    @Override
    public void createUsersTable() {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS users (" +
                             "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                             "name VARCHAR(50) NOT NULL," +
                             "lastName VARCHAR(50) NOT NULL," +
                             "age TINYINT NOT NULL)"
             )) {
            preparedStatement.executeUpdate();
            System.out.println("Table 'users' created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to create table 'users'");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DROP TABLE IF EXISTS users")) {
            preparedStatement.executeUpdate();
            System.out.println("Table 'users' dropped successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to drop table 'users'");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)"
             )) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User '" + name + "' added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add user '" + name + "'");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM users WHERE id=?"
             )) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User with id " + id + " removed successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to remove user with id " + id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM users"
             )) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to retrieve users");
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM users"
             )) {
            preparedStatement.executeUpdate();
            System.out.println("Table 'users' cleaned successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to clean table 'users'");
        }
    }
}
