package gsm.festival.golaroid.domain.post.service;

import gsm.festival.golaroid.domain.image.entity.Image;
import gsm.festival.golaroid.domain.image.repository.ImageRepository;
import gsm.festival.golaroid.domain.post.entity.Post;
import gsm.festival.golaroid.domain.post.exception.PostNotFoundException;
import gsm.festival.golaroid.domain.post.repository.PostRepository;
import gsm.festival.golaroid.domain.presentation.dto.response.QueryPostDetailsResponse;
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
    private final ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public List<QueryPostsResponse> queryPostsService() {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(p ->
                QueryPostsResponse.builder()
                        .id(p.getId())
                        .writer(p.getWriter())
                        .code(p.getCode())
                        .imageUrl(imageRepository.findByPost(p).getImageUrl())
                        .build()
                ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public QueryPostDetailsResponse queryPostDetails(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        Image image = imageRepository.findByPost(post);

        return QueryPostDetailsResponse.builder()
                .id(postId)
                .imageUrl(image.getImageUrl())
                .build();
    }
}
