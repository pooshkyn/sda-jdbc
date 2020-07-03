package pl.sda.support;

import pl.sda.post.domain.Post;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static pl.sda.user.domain.JdbcUserRepository.JDBC_URL;

public interface PostRepositoryAbility extends CommonRepositoryAbility {

    LocalDateTime NOW = LocalDateTime.now(ZoneId.systemDefault());

    default void addPost() {
        Timestamp timestamp = Timestamp.valueOf(NOW);
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("INSERT INTO post (id, text, username, creationdate) VALUES ('1', 'test', 'jan', '" + timestamp.toString() + "')");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default void addPosts() {
        Timestamp timestamp = Timestamp.valueOf(NOW.minusHours(6));
        Timestamp timestamp2 = Timestamp.valueOf(NOW.minusHours(2));
        Timestamp timestamp3 = Timestamp.valueOf(NOW.minusDays(2));

        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("INSERT INTO post (id, text, username, creationdate) VALUES " +
                        "('1', 'test', 'jan', '" + timestamp.toString() + "'), " +
                        "('2', 'lorem ipsum', 'mietek', '" + timestamp2.toString() + "'), " +
                        "('3', 'ipsum lorem', 'jan', '" + timestamp3.toString() + "')");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    default Optional<Post> findPost(int id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet result = statement.executeQuery("SELECT * FROM post")) {
                    while (result.next()) {
                        int u = result.getInt("id");
                        if (u == id) {
                            return Optional.of(Post.builder()
                                    .id(u)
                                    .text(result.getString("text"))
                                    .username(result.getString("username"))
                                    .creationDate(result.getTimestamp("creationDate").toLocalDateTime())
                                    .build()
                            );
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
