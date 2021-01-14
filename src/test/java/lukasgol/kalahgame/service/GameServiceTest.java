package lukasgol.kalahgame.service;

import lukasgol.kalahgame.exception.GameNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static lukasgol.kalahgame.TestHelper.ID;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService();
    }

    @Test
    void shouldThrowGameNotFoundException() {
        assertThrows(GameNotFoundException.class, () -> gameService.getGame(ID));
    }
}