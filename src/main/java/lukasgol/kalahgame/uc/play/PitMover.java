package lukasgol.kalahgame.uc.play;

import lukasgol.kalahgame.constant.BoardConstants;
import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.domain.Pit;
import org.springframework.stereotype.Component;

@Component
public class PitMover {

    private static final int ZERO = 0;
    private static final int ONE = 1;

    Pit move(final Game game, final Pit chosenPit) {
        int stonesNumber = chosenPit.getStoneNumber();
        chosenPit.removePits();
        var currentPitId = chosenPit.getId();
        while (stonesNumber > ZERO) {
            currentPitId++;
            if (currentPitId > BoardConstants.LAST_PIT_INDEX) {
                currentPitId = BoardConstants.FIRST_PIT_INDEX;
            }
            var currentPit = game.getPitById(currentPitId);
            if (currentPit.isDistributable(game.getCurrentPlayer())) {
                currentPit.setStoneNumber(currentPit.getStoneNumber() + ONE);
                stonesNumber--;
            }
        }
        final Pit endPit = game.getPitById(currentPitId);
        resolveEndPit(game, endPit);
        return endPit;
    }

    private void resolveEndPit(final Game game, final Pit endPit) {
        if (!endPit.isHouse() && endPit.getOwner()
                .equals(game.getCurrentPlayer()) && (endPit.getStoneNumber() == ONE)) {
            final Pit oppositePit = game.getOppositePit(endPit);
            if (oppositePit.getStoneNumber() > ZERO) {
                final Pit house = game.getPlayerHouse(game.getCurrentPlayer());
                house.setStoneNumber((house.getStoneNumber() + oppositePit.getStoneNumber()) + endPit.getStoneNumber());
                oppositePit.setStoneNumber(ZERO);
                endPit.setStoneNumber(ZERO);
            }
        }
    }
}
