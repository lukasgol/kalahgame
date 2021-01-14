package lukasgol.kalahgame;

import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.service.GameService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CreateGameIntegrationTest {
    private static final int MILLION = 1000000;


    private GameService gameService;

    @Before
    public void setUp() {

        gameService = new GameService();
    }

    @Test
    public void shouldExecuteMillionTransactionInParallelStream() {
        IntStream.range(0, MILLION)
                .parallel()
                .forEach(value -> gameService.createGame());

        final List<Game> allGames = gameService.getAllGames();
        assertThat(allGames).hasSize(MILLION);
        assertThat(allGames).contains((new Game(MILLION)));
    }

}
