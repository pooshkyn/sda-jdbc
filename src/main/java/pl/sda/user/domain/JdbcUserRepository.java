package pl.sda.user.domain;

import java.util.List;
import java.util.Optional;

public class JdbcUserRepository implements UserRepository {

    /*
    * Na potrzeby kursu wystarczy, że podamy stały URL do bazy. Dobrą i zalecaną praktyką jest jednak, żeby konfiguracja taka jak ta była dostarczana z zewnątrz.
    * */
    public final static String JDBC_URL = "";


    @Override
    public boolean isConnectionValid() {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void changePassword(String username, String password) {

    }

    @Override
    public void deleteByUsername(String username) {

    }
}
