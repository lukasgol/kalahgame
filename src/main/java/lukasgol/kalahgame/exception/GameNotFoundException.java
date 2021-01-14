package lukasgol.kalahgame.exception;

import lukasgol.kalahgame.constant.ErrorCodes;
import org.springframework.http.HttpStatus;

public class GameNotFoundException extends GameException {
    private long gameId;

    public GameNotFoundException(final long gameId) {
        this.gameId = gameId;
    }

    @Override
    public String getErrorCode() {
        return ErrorCodes.GAME_NOT_FOUND;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public Object getValue() {
        return gameId;
    }
}
