package gsm.festival.golaroid.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import gsm.festival.golaroid.global.error.ErrorCode;
import gsm.festival.golaroid.global.error.ErrorResponse;
import gsm.festival.golaroid.global.error.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class ExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final BaseException exception;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            if (e == exception) {
                sendError(response, exception.getErrorCode());
            } else {
                sendError(response, ErrorCode.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private void sendError(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getErrorMessage(), errorCode.getErrorStatus());
        response.setStatus(errorCode.getErrorStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String responseString = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(responseString);
    }
}
