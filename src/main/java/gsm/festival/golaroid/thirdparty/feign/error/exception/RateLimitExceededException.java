package gsm.festival.golaroid.thirdparty.feign.error.exception;

import gsm.festival.golaroid.global.error.ErrorCode;
import gsm.festival.golaroid.global.error.exception.BaseException;

public class RateLimitExceededException extends BaseException {
    public RateLimitExceededException() {
        super(ErrorCode.RATE_LIMIT_EXCEEDED);
    }
}
