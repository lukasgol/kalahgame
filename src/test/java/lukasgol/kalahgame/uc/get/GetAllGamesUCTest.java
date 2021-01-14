package lukasgol.kalahgame.uc.get;

import lukasgol.kalahgame.TestHelper;
import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.dto.GameDto;
import lukasgol.kalahgame.service.GameService;
import lukasgol.kalahgame.uc.GameMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lukasgol.kalahgame.TestHelper.ID;
import static lukasgol.kalahgame.TestHelper.URI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetAllGamesUCTest {

    private GetAllGamesUC getAllGamesUC;
    private GameService gameService;
    private GameMapper gameMapper;

    @BeforeEach
    void setUp() {

        gameMapper = mock(GameMapper.class);
        gameService = mock(GameService.class);
        getAllGamesUC = new GetAllGamesUC(gameService, gameMapper);
    }

    @Test
    void shouldReturnAllGames() {
        //given
        final Game game = TestHelper.createGame();
        when(gameService.getAllGames()).thenReturn(List.of(game));
        final GameDto gameDto = new GameDto(URI, ID);
        when(gameMapper.mapGameToGameDto(game)).thenReturn(gameDto);
        //when
        final List<GameDto> allGames = getAllGamesUC.getAllGames();
        //then
        assertThat(allGames).hasSize(1)
                .contains(gameDto);
    }
}