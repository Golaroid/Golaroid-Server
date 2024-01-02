package gsm.festival.golaroid.domain.image.exception;

import gsm.festival.golaroid.global.error.ErrorCode;
import gsm.festival.golaroid.global.error.exception.BaseException;

public class ImageNotFoundException extends BaseException {
    public ImageNotFoundException() {
        super(ErrorCode.IMAGE_NOT_FOUND);
    }
}
