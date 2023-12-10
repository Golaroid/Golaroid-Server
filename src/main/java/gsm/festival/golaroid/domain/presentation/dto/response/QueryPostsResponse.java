package gsm.festival.golaroid.domain.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QueryPostsResponse {
    private final Long id;
    private final String writer;
    private final String code;
    private final String imageUrl;
}
