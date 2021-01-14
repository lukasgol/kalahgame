package lukasgol.kalahgame.exception;

import lukasgol.kalahgame.constant.ErrorCodes;
import org.springframework.http.HttpStatus;

public class IllegalArgumentException extends GameException {

    private final String argument;
    private final Object value;

    public IllegalArgumentException(final String argument, final Object value) {
        this.argument = argument;
        this.value = value;
    }

    @Override
    public String getErrorCode() {
        return ErrorCodes.INCORRECT_ARGUMENT;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public Object getValue() {
        return argument + ": " + value;
    }
}
