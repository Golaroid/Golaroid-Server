package gsm.festival.golaroid.global.error.exception;

import gsm.festival.golaroid.global.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private ErrorCode errorCode;
}
