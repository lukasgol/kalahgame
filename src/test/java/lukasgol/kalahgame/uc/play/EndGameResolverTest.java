package lukasgol.kalahgame.uc.play;

import lukasgol.kalahgame.TestHelper;
import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.domain.Pit;
import lukasgol.kalahgame.domain.Player;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static lukasgol.kalahgame.TestHelper.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EndGameResolverTest {

    private final EndGameResolver endGameResolver = new EndGameResolver();

    @Test
    void givenNewGameShouldReturnFalse() {
        //given
        final Game game = TestHelper.createGame();
        //when
        //then
        assertFalse(endGameResolver.isGameEnded(game));
    }

    @Test
    void givenEndedGameShouldReturnTrue() {
        //given
        final Game game = createEndedGame();
        //when
        //then
        assertTrue(endGameResolver.isGameEnded(game));
    }

    @Test
    void whenDrawShouldReturnEmpty() {
        //given
        final Game game = createEndedDrawGame();
        //when
        final Optional<Player> winner = endGameResolver.findWinner(game);
        //then
        assertThat(winner).isEmpty();
    }

    @Test
    void whenFirstPlayerHasMoreStonesShouldReturnFirstPlayer() {
        //given
        final Game game = createFPWinnerGame();
        //when
        final Optional<Player> winner = endGameResolver.findWinner(game);
        //then
        assertThat(winner).isNotEmpty()
                .contains(Player.FIRST_PLAYER);
    }

    private Game createFPWinnerGame() {
        final Map<Integer, Pit> board = Map.of(1, Pit.createPit(1, Player.FIRST_PLAYER, 2), 2, Pit.createPit(2, Player.FIRST_PLAYER, 0), 3,
                Pit.createHouse(3, Player.FIRST_PLAYER, 10), 4, Pit.createPit(4, Player.SECOND_PLAYER, 0), 5,
                Pit.createPit(4, Player.SECOND_PLAYER, 1), 6, Pit.createHouse(6, Player.SECOND_PLAYER, 10));
        return new Game(ID, board);
    }

    private Game createEndedDrawGame() {
        final Map<Integer, Pit> board =
                Map.of(1, Pit.createPit(1, Player.FIRST_PLAYER, 0), 2, Pit.createHouse(2, Player.FIRST_PLAYER, 10), 3,
                        Pit.createPit(3, Player.SECOND_PLAYER, 0), 4, Pit.createHouse(5, Player.SECOND_PLAYER, 10));
        return new Game(ID, board);

    }

    private Game createEndedGame() {
        final Map<Integer, Pit> board =
                Map.of(1, Pit.createPit(1, Player.FIRST_PLAYER, 1), 2, Pit.createHouse(2, Player.FIRST_PLAYER, 10), 3,
                        Pit.createPit(3, Player.SECOND_PLAYER, 0), 4, Pit.createHouse(5, Player.SECOND_PLAYER, 10));
        return new Game(ID, board);
    }


}