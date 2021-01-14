package lukasgol.kalahgame;

import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.domain.Pit;
import lukasgol.kalahgame.domain.Player;
import lukasgol.kalahgame.domain.Status;
import lukasgol.kalahgame.service.GameService;
import lukasgol.kalahgame.uc.play.PlayUC;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayIntegrationTest {

    @Autowired
    private PlayUC playUC;
    @Autowired
    private GameService gameService;
    private static final List<Integer> MOVES =
            List.of(1, 2, 8, 1, 11, 1, 13, 4, 13, 12, 13, 11, 1, 9, 6, 2, 13, 8, 1, 11, 3, 13, 12, 13, 10, 1, 13, 9, 6, 11, 2, 12, 8, 5, 13,
                    8, 6, 3, 9, 6, 4, 10, 5, 6);

    @Test
    public void should() {
        final Game game = gameService.createGame();
        MOVES.forEach(moveId -> playUC.movePit(game.getId(), moveId));
        assertThat(game.getStatus()).isEqualTo(Status.WINNER_DETERMINED);
        assertThat(game.getWinner()).isEqualTo(Player.SECOND_PLAYER);
        assertThat(game.getBoard()).isEqualTo(createExpectedBoard());
    }

    public static Map<Integer, Pit> createExpectedBoard() {
        final Map<Integer, Pit> board = new HashMap<>();
        board.put(1, Pit.createPit(1, Player.FIRST_PLAYER, 0));
        board.put(2, Pit.createPit(2, Player.FIRST_PLAYER, 0));
        board.put(3, Pit.createPit(3, Player.FIRST_PLAYER, 0));
        board.put(4, Pit.createPit(4, Player.FIRST_PLAYER, 0));
        board.put(5, Pit.createPit(5, Player.FIRST_PLAYER, 0));
        board.put(6, Pit.createPit(6, Player.FIRST_PLAYER, 0));
        board.put(7, Pit.createHouse(7, Player.FIRST_PLAYER, 23));
        board.put(8, Pit.createPit(8, Player.SECOND_PLAYER, 0));
        board.put(9, Pit.createPit(9, Player.SECOND_PLAYER, 0));
        board.put(10, Pit.createPit(10, Player.SECOND_PLAYER, 0));
        board.put(11, Pit.createPit(11, Player.SECOND_PLAYER, 2));
        board.put(12, Pit.createPit(12, Player.SECOND_PLAYER, 0));
        board.put(13, Pit.createPit(13, Player.SECOND_PLAYER, 0));
        board.put(14, Pit.createHouse(14, Player.SECOND_PLAYER, 47));
        return board;
    }
}
