package lukasgol.kalahgame.uc.play;

import lukasgol.kalahgame.constant.BoardConstants;
import lukasgol.kalahgame.constant.ErrorCodes;
import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.domain.Pit;
import lukasgol.kalahgame.exception.IllegalArgumentException;
import lukasgol.kalahgame.exception.IllegalMoveException;
import lukasgol.kalahgame.exception.OpponentsTurnException;
import org.springframework.stereotype.Component;

@Component
public class MoveValidator {

    void validate(final Game game, final Integer pitId) {
        if (pitId < BoardConstants.FIRST_PIT_INDEX || pitId > BoardConstants.LAST_PIT_INDEX) {
            throw new IllegalArgumentException("pitId", pitId);
        }
        final Pit pit = game.getPitById(pitId);
        if (pit.isHouse()) {
            throw new IllegalMoveException(ErrorCodes.HOUSE_MOVE_NOT_ALLOWED);
        }

        if (pit.getOwner() != game.getCurrentPlayer()) {
            throw new OpponentsTurnException(game.getCurrentPlayer());
        }

        if (pit.getStoneNumber() == 0) {
            throw new IllegalMoveException(ErrorCodes.EMPTY_PIT);
        }
    }
}
