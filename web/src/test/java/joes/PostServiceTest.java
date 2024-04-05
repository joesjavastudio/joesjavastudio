package joes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(PostService.class)
class PostServiceTest {

    @Autowired
    MockRestServiceServer server;

    @Autowired
    PostService postService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void findAll() throws JsonProcessingException {
        // given
        List<Post> data = List.of(
                new Post(1, 1, "Hello, World!", "This is my first post!"),
                new Post(2, 1, "Testing Rest Client with @RestClientTest", "This is a post about testing RestClient calls")
        );

        // when
        this.server
                .expect(requestTo("https://jsonplaceholder.typicode.com/posts"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(data), MediaType.APPLICATION_JSON));


        // then
        List<Post> result = postService.findAll();
        assertEquals(data, result);
    }
}