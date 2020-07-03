package pl.sda.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static pl.sda.user.domain.JdbcUserRepository.JDBC_URL;

public interface CommonRepositoryAbility {
    default void truncateTable(String table) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            String query = "TRUNCATE TABLE " + table;
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
