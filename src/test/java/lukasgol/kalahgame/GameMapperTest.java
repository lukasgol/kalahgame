package lukasgol.kalahgame;

import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.domain.Pit;
import lukasgol.kalahgame.domain.Player;
import lukasgol.kalahgame.dto.GameDto;
import lukasgol.kalahgame.dto.MoveResponseDto;
import lukasgol.kalahgame.uc.GameMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static lukasgol.kalahgame.TestHelper.ID;
import static lukasgol.kalahgame.TestHelper.createGameDto;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class GameMapperTest {


    @Autowired
    private GameMapper gameMapper;

    @Test
    void givenGameShouldReturnGameDto() {
        //given
        final Game game = createGame();
        final GameDto expectedGameDto = createGameDto();
        //when
        final GameDto gameDto = gameMapper.mapGameToGameDto(game);
        //then
        assertThat(gameDto).isEqualTo(expectedGameDto);
    }

    @Test
    void givenGameShouldReturnMoveResponse() {
        //given
        final Game game = createGame();
        final MoveResponseDto expectedMoveResponse = TestHelper.createMoveResponse();
        //when
        final MoveResponseDto moveResponse = gameMapper.mapGameToMoveResponse(game);
        //then
        assertThat(moveResponse).isEqualTo(expectedMoveResponse);
    }

    private Game createGame() {
        final Map<Integer, Pit> board = Map.of(1, Pit.createHouse(1, Player.FIRST_PLAYER), 2, Pit.createPit(2, Player.FIRST_PLAYER, 6));
        return new Game(ID, board);
    }
}