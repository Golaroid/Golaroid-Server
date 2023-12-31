package gsm.festival.golaroid.domain.post.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QueryPostsResponse {
    private final Long postId;
    private final String writer;
    private final String code;
    private final String imageUrl;
}
