package pl.sda.post.domain;

import java.util.List;
import java.util.Optional;

interface PostRepository {
    //Połącz się do bazy i zwróć flagę czy się udało (isValid)
    boolean isConnectionValid();

    //Znajdź posty użytkownika
    List<Post> findUserPosts(String username);

    //Dodaj post
    void addPost(Post post);

    //Pobierz posty z dnia dzisiejszego
    List<Post> findDailyPosts();

    //Znajdź post po identyfikatorze
    Optional<Post> findById(int id);

    //Zmień treść posta
    void editPost(int id, String newText);

    //Usuń post
    void deletePost(int id);
}
