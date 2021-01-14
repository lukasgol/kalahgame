package lukasgol.kalahgame.uc.play;

import lukasgol.kalahgame.TestHelper;
import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.exception.IllegalArgumentException;
import lukasgol.kalahgame.exception.IllegalMoveException;
import lukasgol.kalahgame.exception.OpponentsTurnException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MoveValidatorTest {

    private final MoveValidator moveValidator = new MoveValidator();

    @ParameterizedTest()
    @ValueSource(ints = {0, 15, 20, Integer.MAX_VALUE})
    void givenIllegalPitIdShouldThrowIllegalArgumentException(int pitId) {
        //given
        final Game game = TestHelper.createGame();
        assertThrows(IllegalArgumentException.class, () -> moveValidator.validate(game, pitId));
    }

    @ParameterizedTest()
    @ValueSource(ints = {7, 14})
    void whenTryToMoveStonesFromHouseThrowIllegalMoveException(int pitId) {
        //given
        final Game game = TestHelper.createGame();
        assertThrows(IllegalMoveException.class, () -> moveValidator.validate(game, pitId));
    }

    @Test
    void whenTryToMoveStonesFromEmptyPitThrowIllegalMoveException() {
        //given
        final Game game = TestHelper.createGame();
        final int pitId = 1;
        game.getBoard()
                .get(pitId)
                .removePits();
        assertThrows(IllegalMoveException.class, () -> moveValidator.validate(game, pitId));
    }

    @Test
    void whenTryToPlayOpponentTurnShouldThrowOpponentsTurnException() {
        //given
        final Game game = TestHelper.createGame();
        assertThrows(OpponentsTurnException.class, () -> moveValidator.validate(game, 9));
    }


    @Test
    void shouldDoNothingWhenCorrectMove() {
        //given
        final Game game = TestHelper.createGame();
        //when
        moveValidator.validate(game, 1);
        //then nothing
    }
}