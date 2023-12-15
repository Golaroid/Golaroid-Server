package gsm.festival.golaroid.thirdparty.feign.error.exception;

import gsm.festival.golaroid.global.error.ErrorCode;
import gsm.festival.golaroid.global.error.exception.BaseException;

public class MissionApiException extends BaseException {
    public MissionApiException() {
        super(ErrorCode.MISSING_API_KEY);
    }
}
