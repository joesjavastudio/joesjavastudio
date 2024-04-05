package joes;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostService {

    private final RestClient restClient;

    public PostService(RestClient.Builder builder) {
        restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    List<Post> findAll() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    Post create(Post post) {
        return restClient.post()
                .uri("/posts")
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    Post findById(int id) {
        return restClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .onStatus(
                        httpStatusCode -> httpStatusCode.value() == 404,
                        ((request,response) -> {
                            throw new ResponseStatusException(HttpStatusCode.valueOf(404),
                                    "id is invalid");
                        })
                )
                .body(Post.class);

    }

    Post update(Post post, int id) {
        return restClient.put()
                .uri("/posts/{id}", id)
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    void delete(int id) {
        restClient.delete()
                .uri("/posts/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
