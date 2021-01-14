package lukasgol.kalahgame.configuration;

import lukasgol.kalahgame.exception.GameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(value = GameException.class)
    public ResponseEntity<ErrorResponse> handleIGameException(final GameException e) {
        return new ResponseEntity<>(new ErrorResponse(e), e.getHttpStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
