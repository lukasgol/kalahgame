package lukasgol.kalahgame.uc.play;

import lukasgol.kalahgame.domain.Pit;
import lukasgol.kalahgame.domain.Player;
import org.junit.jupiter.api.Test;

import static lukasgol.kalahgame.TestHelper.ID;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTurnChooserTest {
    private final PlayerTurnChooser playerTurnChooser = new PlayerTurnChooser();
    private static final Player CURRENT_PLAYER = Player.FIRST_PLAYER;
    private static final Player OPPOSITE_PLAYER = Player.SECOND_PLAYER;
    private static final int STONE_NO = 3;

    @Test
    void whenPitIsNotHouseShouldReturnOppositePlayer() {
        //given
        Pit pit = Pit.createPit(ID, CURRENT_PLAYER, STONE_NO);
        //when
        final Player nextPlayer = playerTurnChooser.choosePlayer(CURRENT_PLAYER, pit);
        //then
        assertThat(nextPlayer).isEqualByComparingTo(OPPOSITE_PLAYER);
    }

    @Test
    void whenPitIsHouseWhichBelongsToCurrentPlayerShouldReturnCurrentPlayer() {
        //given
        Pit pit = Pit.createHouse(ID, CURRENT_PLAYER, STONE_NO);
        //when
        final Player nextPlayer = playerTurnChooser.choosePlayer(CURRENT_PLAYER, pit);
        //then
        assertThat(nextPlayer).isEqualByComparingTo(CURRENT_PLAYER);
    }

    @Test
    void whenPitIsHouseWhichBelongsToOppositePlayerShouldThrowIllegalStateException() {
        //given
        Pit pit = Pit.createHouse(ID, OPPOSITE_PLAYER, STONE_NO);
        //when
        //then
        assertThrows(IllegalStateException.class, () -> playerTurnChooser.choosePlayer(CURRENT_PLAYER, pit));
    }
}