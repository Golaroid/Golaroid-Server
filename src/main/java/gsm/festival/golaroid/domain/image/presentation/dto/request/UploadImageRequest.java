package gsm.festival.golaroid.domain.image.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class UploadImageRequest {
    @NonNull
    private final Boolean isPublic;
    @NotBlank
    private final String writer;
}
