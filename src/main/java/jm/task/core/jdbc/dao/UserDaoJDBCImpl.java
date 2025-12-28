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

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sqlQuery = "CREATE TABLE IF NOT EXISTS users (Id BIGINT NOT NULL AUTO_INCREMENT, " +
                "Name VARCHAR(100), " +
                "LastName VARCHAR(100), " +
                "Age INT, " +
                "PRIMARY KEY (Id))";

        try (Connection conn = Util.getConnection()) {

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        String sqlQuery = "DROP TABLE IF EXISTS users";

        try (Connection conn = Util.getConnection()) {

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            Util.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sqlQuery = "INSERT INTO users (Name, LastName, Age) VALUES (?, ?, ?)";

        try (Connection conn = Util.getConnection()) {

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        String sqlQuery = "DELETE FROM users WHERE Id = ?";

        try (Connection conn = Util.getConnection()) {

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        String sqlQuery = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection conn = Util.getConnection()) {

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("Id"));
                user.setName(resultSet.getString("Name"));
                user.setLastName(resultSet.getString("LastName"));
                user.setAge(resultSet.getByte("Age"));
                users.add(user);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {

        String sqlQuery = "TRUNCATE TABLE Users";

        try (Connection conn = Util.getConnection()) {

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
