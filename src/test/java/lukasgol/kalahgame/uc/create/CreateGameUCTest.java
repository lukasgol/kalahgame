package lukasgol.kalahgame.uc.create;

import lukasgol.kalahgame.TestHelper;
import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.dto.GameDto;
import lukasgol.kalahgame.service.GameService;
import lukasgol.kalahgame.uc.GameMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static lukasgol.kalahgame.TestHelper.URI;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateGameUCTest {

    private static final int ID = 1;
    private CreateGameUC createGameUC;
    private GameService gameService;
    private GameMapper gameMapper;

    @BeforeEach
    void setUp() {
        this.gameService = mock(GameService.class);
        this.gameMapper = mock(GameMapper.class);
        createGameUC = new CreateGameUC(gameService, gameMapper);
    }

    @Test
    void shouldCreateNewGame() {
        //given
        final Game game = TestHelper.createGame();
        when(gameService.createGame()).thenReturn(game);
        when(gameMapper.mapGameToGameDto(game)).thenReturn(new GameDto(URI, ID));
        //when
        createGameUC.createGame();
        //then
        verify(gameService).createGame();
        verify(gameMapper).mapGameToGameDto(game);
    }
}