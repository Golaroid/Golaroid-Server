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

    INTERNAL_SERVER_ERROR("INTERNAL SERVER ERROR", 500);

    private final String errorMessage;
    private final int errorStatus;
}
