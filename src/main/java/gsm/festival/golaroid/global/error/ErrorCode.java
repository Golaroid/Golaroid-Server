package gsm.festival.golaroid.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR("INTERNAL SERVER ERROR", 500);

    private final String errorMessage;
    private final int errorStatus;
}
