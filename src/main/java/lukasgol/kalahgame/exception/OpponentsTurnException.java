package lukasgol.kalahgame.exception;

import lukasgol.kalahgame.constant.ErrorCodes;
import lukasgol.kalahgame.domain.Player;
import org.springframework.http.HttpStatus;

public class OpponentsTurnException extends GameException {

    private final String currentPlayer;

    public OpponentsTurnException(final Player currentPlayer) {this.currentPlayer = currentPlayer.getName();}

    @Override
    public String getErrorCode() {
        return ErrorCodes.OPPONENTS_PIT;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public Object getValue() {
        return "currentTurn: " + currentPlayer;
    }
}
