package gsm.festival.golaroid.domain.presentation;

import gsm.festival.golaroid.domain.post.service.PostService;
import gsm.festival.golaroid.domain.presentation.dto.response.QueryPostDetailsResponse;
import gsm.festival.golaroid.domain.presentation.dto.response.QueryPostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/{post_id}")
    public ResponseEntity<QueryPostDetailsResponse> queryPostById(@PathVariable("post_id") Long postId) {
        QueryPostDetailsResponse response = postService.queryPostDetails(postId);
        return ResponseEntity.ok(response);
    }
}
