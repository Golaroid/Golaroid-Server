package gsm.festival.golaroid.domain.image.exception;

import gsm.festival.golaroid.global.error.ErrorCode;
import gsm.festival.golaroid.global.error.exception.BaseException;

public class NotValidExtensionException extends BaseException {
    public NotValidExtensionException() {
        super(ErrorCode.NOT_VALID_EXTENSION);
    }
}
