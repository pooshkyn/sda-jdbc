package pl.sda.post.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.support.PostRepositoryAbility;
import pl.sda.support.UserRepositoryAbility;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostRepositoryTest implements PostRepositoryAbility, UserRepositoryAbility {

    private final static String USERNAME = "jan";
    private final static int ID = 1;
    private final static Post POST = new Post(1, "test", "jan", NOW);
    private final static Post POST2 = new Post(2, "lorem ipsum", "mietek", NOW.minusDays(1));
    private final static Post POST3 = new Post(3, "ipsum lorem", "jan", NOW.minusHours(4));

    PostRepository postRepository = new JdbcPostRepository();

    @BeforeEach
    void init() {
        truncateTable("post");
    }

    @Test
    void testConnectionToDatabase() {
        //when
        boolean isConnectionValid = postRepository.isConnectionValid();
        //then
        assertTrue(isConnectionValid);
    }

    @Test
    void testFindUserPosts() {
        //given
        addPosts();
        //when
        List<Post> result = postRepository.findUserPosts(USERNAME);
        //then
        assertTrue(result.contains(POST));
        assertTrue(result.contains(POST3));
    }

    @Test
    void testAddPost() {
        //when
        postRepository.addPost(POST);
        //then
        Optional<Post> post = findPost(ID);
        assertTrue(post.isPresent());
        assertEquals(POST, post.get());
    }

    @Test
    void testFindDailyPosts() {
        //given
        addPosts();
        //when
        List<Post> result = postRepository.findDailyPosts();
        //then
        assertTrue(result.contains(POST));
        assertTrue(result.contains(POST2));
    }

    @Test
    void testFindById() {
        //given
        addPosts();
        //when
        Optional<Post> result = postRepository.findById(ID);
        //then
        assertTrue(result.isPresent());
        assertEquals(POST, result.get());
    }

    @Test
    void testEditPost() {
        //given
        String newContent = "some new content";
        Post expectedPost = new Post(1, newContent, "jan", NOW);
        addPosts();
        //when
        postRepository.editPost(ID, "some new content");
        //then
        Optional<Post> post = findPost(ID);
        assertTrue(post.isPresent());
        assertEquals(expectedPost, post.get());
    }

    @Test
    void testDeletePost() {
        //given
        addPosts();
        //when
        postRepository.deletePost(1);
        //then
        assertFalse(findPost(ID).isPresent());
    }
}

