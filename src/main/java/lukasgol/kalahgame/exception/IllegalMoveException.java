package lukasgol.kalahgame.exception;

import org.springframework.http.HttpStatus;

public class IllegalMoveException extends GameException {
    private final String errorCode;

    public IllegalMoveException(final String errorCode) {this.errorCode = errorCode;}

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
