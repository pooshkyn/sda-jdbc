package pl.sda.support;


import pl.sda.user.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

import static pl.sda.user.domain.JdbcUserRepository.JDBC_URL;

public interface UserRepositoryAbility extends CommonRepositoryAbility {

    default void addUser() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("INSERT INTO appuser (username, password) VALUES ('jan', 'alternatywy4')");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default void addUsers() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("INSERT INTO appuser (username, password) VALUES " +
                        "('jan', 'alternatywy4'), ('testuser', 'haslo'), ('mietek', 'qwerty123')");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Optional<User> findUser(String username) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet result = statement.executeQuery("SELECT * FROM appuser")) {
                    while (result.next()) {
                        String u = result.getString("username");
                        if (u.equals(username)) {
                            return Optional.of(new User(u, result.getString("password")));
                        }
                    }
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
