package gsm.festival.golaroid.domain.post.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QueryPostDetailsResponse {
    private final Long id;
    private final String imageUrl;
}
