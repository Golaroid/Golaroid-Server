package gsm.festival.golaroid.global.exception;

import gsm.festival.golaroid.global.error.ErrorCode;
import gsm.festival.golaroid.global.error.exception.BaseException;

public class InternalServerError extends BaseException {
    public InternalServerError() {
        super(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
