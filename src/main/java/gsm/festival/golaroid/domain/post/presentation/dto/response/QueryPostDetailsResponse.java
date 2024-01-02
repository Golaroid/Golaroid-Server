package gsm.festival.golaroid.domain.post.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class QueryPostDetailsResponse {
    private final Long postId;
    private final List<String> imageUrl;
    private final String writer;
}
