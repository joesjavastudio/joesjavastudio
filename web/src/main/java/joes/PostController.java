package joes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    ResponseEntity<List<Post>> findAll() {
        return postService.findAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("findById")
    Post findById(@RequestParam(name="id") Integer postId) {
        return postService.findById(postId);
    }

    @PutMapping("")
    Post update(@RequestBody Post post, @RequestParam Integer id) {
        return postService.update(post, id);
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@RequestParam Integer id) {
        postService.delete(id);
    }

    @GetMapping("test")
    void test(@RequestParam List<String> id) {
        System.out.println(id);
    }

}
