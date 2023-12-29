package gsm.festival.golaroid.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final String errorMessage;
    private final int errorStatus;
}
