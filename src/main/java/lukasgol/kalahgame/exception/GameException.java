package lukasgol.kalahgame.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

public abstract class GameException extends RuntimeException {

    @JsonInclude(JsonInclude.Include.NON_NULL)

    public abstract String getErrorCode();

    public abstract HttpStatus getHttpStatus();

    public Object getValue() {
        return null;
    }

}
