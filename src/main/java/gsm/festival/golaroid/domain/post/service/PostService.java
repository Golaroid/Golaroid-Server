package gsm.festival.golaroid.domain.post.service;

import gsm.festival.golaroid.domain.image.entity.Image;
import gsm.festival.golaroid.domain.image.exception.ImageNotFoundException;
import gsm.festival.golaroid.domain.image.repository.ImageRepository;
import gsm.festival.golaroid.domain.post.entity.Post;
import gsm.festival.golaroid.domain.post.entity.constant.DisclosureStatus;
import gsm.festival.golaroid.domain.post.exception.PostNotFoundException;
import gsm.festival.golaroid.domain.post.repository.PostRepository;
import gsm.festival.golaroid.domain.post.presentation.dto.response.QueryPostDetailsResponse;
import gsm.festival.golaroid.domain.post.presentation.dto.response.QueryPostsResponse;
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
        List<Post> posts = postRepository.findAllByDisclosureStatus(DisclosureStatus.PUBLIC);

        return posts.stream().map(post ->
                QueryPostsResponse.builder()
                        .postId(post.getId())
                        .writer(post.getWriter())
                        .code(post.getCode())
                        .imageUrl(imageRepository.findByPost(post)
                                .orElseThrow(ImageNotFoundException::new).getImageUrl())
                        .build()
                ).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public QueryPostDetailsResponse queryPostDetails(String code) {
        Post post = postRepository.findByCode(code)
                .orElseThrow(PostNotFoundException::new);
        Image image = imageRepository.findByPost(post)
                .orElseThrow(ImageNotFoundException::new);

        return QueryPostDetailsResponse.builder()
                .postId(post.getId())
                .imageUrl(image.getImageUrl())
                .writer(post.getWriter())
                .build();
    }
}
