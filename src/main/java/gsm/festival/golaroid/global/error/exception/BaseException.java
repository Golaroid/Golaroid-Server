package gsm.festival.golaroid.global.error.exception;

import gsm.festival.golaroid.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private ErrorCode errorCode;
}
