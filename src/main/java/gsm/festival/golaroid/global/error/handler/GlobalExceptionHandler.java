package gsm.festival.golaroid.global.error.handler;

import gsm.festival.golaroid.global.error.ErrorCode;
import gsm.festival.golaroid.global.error.ErrorResponse;
import gsm.festival.golaroid.global.error.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException exception) {
        log.warn("Error Message : " + exception.getMessage());
        ErrorCode errorCode = exception.getErrorCode();
        return new ResponseEntity(errorCode.getErrorMessage(), HttpStatus.valueOf(errorCode.getErrorStatus()));
    }
}
