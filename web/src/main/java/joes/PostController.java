package joes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    List<Post> findAll() {
        return postService.findAll();
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @GetMapping("/{id}")
    Post findByPathVariableId(@PathVariable("id") Integer postId) {
        return postService.findById(postId);
    }

    @GetMapping("/{id}/{name}")
    String findByIdAndName(@PathVariable Map<String, String> pathVariableMap) {
        return pathVariableMap.toString();
    }

    @GetMapping(value = {"/optional", "/optional/{id}"})
    String returnOptionalId(@PathVariable Optional<Integer> id) {
        int resultId = 0;
        if(id.isPresent())
            resultId = id.get();
        return "ID: " + resultId;
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
