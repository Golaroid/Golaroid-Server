package gsm.festival.golaroid.thirdparty.feign.error.exception;

import gsm.festival.golaroid.global.error.ErrorCode;
import gsm.festival.golaroid.global.error.exception.BaseException;

public class InsufficientCreditsException extends BaseException {
    public InsufficientCreditsException() {
        super(ErrorCode.INSUFFICIENT_CREDITS);
    }
}
