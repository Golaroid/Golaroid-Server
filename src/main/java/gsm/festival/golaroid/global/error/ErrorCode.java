package gsm.festival.golaroid.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // post
    POST_NOT_FOUND("존재하지 않는 게시물입니다.", 404),

    INTERNAL_SERVER_ERROR("INTERNAL SERVER ERROR", 500);

    private final String errorMessage;
    private final int errorStatus;
}
