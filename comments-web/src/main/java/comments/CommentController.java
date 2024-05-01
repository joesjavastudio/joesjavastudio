package comments;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @GetMapping("")
    String getMessage() {
        return "You are hitting the comments api!";
    }



}
