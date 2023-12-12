package gsm.festival.golaroid.domain.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadImageRequest {
    private final Boolean isPublic;
    private final String writer;
}
