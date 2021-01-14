package lukasgol.kalahgame;

import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.domain.Pit;
import lukasgol.kalahgame.domain.Player;
import lukasgol.kalahgame.dto.GameDto;
import lukasgol.kalahgame.dto.MoveResponseDto;

import java.util.HashMap;
import java.util.Map;

public class TestHelper {

    public static final int ID = 1;
    public static final String URI = "http://localhost:8080/games/" + ID;
    private static final Map<Integer, Integer> GAME_STATUS = Map.of(1, 0, 2, 6);

    public static Game createGame() {
        final Map<Integer, Pit> board = new HashMap<>();
        board.put(1, Pit.createPit(1, Player.FIRST_PLAYER, 6));
        board.put(2, Pit.createPit(2, Player.FIRST_PLAYER, 6));
        board.put(3, Pit.createPit(3, Player.FIRST_PLAYER, 6));
        board.put(4, Pit.createPit(4, Player.FIRST_PLAYER, 6));
        board.put(5, Pit.createPit(5, Player.FIRST_PLAYER, 6));
        board.put(6, Pit.createPit(6, Player.FIRST_PLAYER, 6));
        board.put(7, Pit.createHouse(7, Player.FIRST_PLAYER));
        board.put(8, Pit.createPit(8, Player.SECOND_PLAYER, 6));
        board.put(9, Pit.createPit(9, Player.SECOND_PLAYER, 6));
        board.put(10, Pit.createPit(10, Player.SECOND_PLAYER, 6));
        board.put(11, Pit.createPit(11, Player.SECOND_PLAYER, 6));
        board.put(12, Pit.createPit(12, Player.SECOND_PLAYER, 6));
        board.put(13, Pit.createPit(13, Player.SECOND_PLAYER, 6));
        board.put(14, Pit.createHouse(14, Player.SECOND_PLAYER));
        return createGame(board);
    }

    public static GameDto createGameDto() {
        return new GameDto(URI, ID);
    }

    public static MoveResponseDto createMoveResponse() {
        return new MoveResponseDto(ID, URI, GAME_STATUS);
    }

    public static Game createGame(final Map<Integer, Pit> startBoard) {
        final Game game = new Game(ID, startBoard);

        game.setCurrentPlayer(Player.FIRST_PLAYER);
        return game;
    }
}
