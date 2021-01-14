package lukasgol.kalahgame.uc.play;

import lukasgol.kalahgame.domain.Pit;
import lukasgol.kalahgame.domain.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerTurnChooser {

    Player choosePlayer(final Player currentPlayer, final Pit pit) {
        if (pit.isHouse()) {
            if (currentPlayer.equals(pit.getOwner())) {
                return pit.getOwner();
            } else {
                throw new IllegalStateException("illegal state: current player shouldn't play user opponent house");
            }
        } else {
            return currentPlayer.getOpponent();
        }
    }
}