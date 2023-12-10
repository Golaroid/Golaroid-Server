package gsm.festival.golaroid.domain.post.exception;

import gsm.festival.golaroid.global.error.ErrorCode;
import gsm.festival.golaroid.global.error.exception.BaseException;

public class PostNotFoundException extends BaseException {
    public PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }
}
