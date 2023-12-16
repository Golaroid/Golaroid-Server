package gsm.festival.golaroid.domain.post.presentation;

import gsm.festival.golaroid.domain.post.presentation.dto.response.QueryPostDetailsResponse;
import gsm.festival.golaroid.domain.post.service.PostService;
import gsm.festival.golaroid.domain.post.presentation.dto.response.QueryPostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<QueryPostsResponse>> queryPosts() {
        List<QueryPostsResponse> response = postService.queryPostsService();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail")
    public ResponseEntity<QueryPostDetailsResponse> queryPostByPostCode(@RequestParam String code) {
        QueryPostDetailsResponse response = postService.queryPostDetails(code);
        return ResponseEntity.ok(response);
    }
}
