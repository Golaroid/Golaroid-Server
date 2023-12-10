package gsm.festival.golaroid.domain.post.service;

import gsm.festival.golaroid.domain.post.entity.Post;
import gsm.festival.golaroid.domain.post.repository.PostRepository;
import gsm.festival.golaroid.domain.presentation.dto.response.QueryPostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<QueryPostsResponse> queryPostsService() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(p ->
                new QueryPostsResponse(
                        p.getId(),
                        p.getWriter(),
                        p.getCode()
                )).collect(Collectors.toList());
    }
}
