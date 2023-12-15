package gsm.festival.golaroid.thirdparty.feign.error.exception;

import gsm.festival.golaroid.global.error.ErrorCode;
import gsm.festival.golaroid.global.error.exception.BaseException;

public class MultipleSourceException extends BaseException {
    public MultipleSourceException() {
        super(ErrorCode.MULTIPLE_SOURCE);
    }
}
