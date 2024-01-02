package gsm.festival.golaroid.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // post
    POST_NOT_FOUND("존재하지 않는 게시물입니다.", 404),

    // image
    NOT_VALID_EXTENSION("유효하지 않은 확장자입니다.", 400),
    IMAGE_NOT_FOUND("존재하지 않는 이미지입니다.", 404),

    // remove bg
    MULTIPLE_SOURCE("Multiple image sources given: Please provide either the image_url, image_file or image_file_b64 parameter.", 400),
    INSUFFICIENT_CREDITS("CREDIT 이 부족합니다.", 402),
    MISSING_API_KEY("Missing API Key", 403),
    RATE_LIMIT_EXCEEDED("Rate limit exceeded", 429),

    INTERNAL_SERVER_ERROR("INTERNAL SERVER ERROR", 500);

    private final String errorMessage;
    private final int errorStatus;
}
