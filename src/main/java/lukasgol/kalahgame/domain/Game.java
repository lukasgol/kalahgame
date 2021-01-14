package lukasgol.kalahgame.domain;

import lukasgol.kalahgame.constant.BoardConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Game {
    private static final int TWO = 2;
    private int id;
    private Status status;
    private Player winner;
    private Player currentPlayer;
    private Map<Integer, Pit> board;

    public Pit getOppositePit(final Pit pit) {
        return getPitById(BoardConstants.LAST_PIT_INDEX - pit.getId());
    }

    public Game(final int id, final Map<Integer, Pit> board) {
        this.id = id;
        this.board = board;
    }

    public Game(final int id) {
        this.id = id;
        this.status = Status.IN_PROGRESS;
        this.currentPlayer = Player.FIRST_PLAYER;
        this.board = createBoard();
    }

    public Pit getPitById(final int pitId) {
        return getBoard().get(pitId);
    }

    private Map<Integer, Pit> createBoard() {
        Map<Integer, Pit> board = new HashMap<>(BoardConstants.LAST_PIT_INDEX);
        for (int i = BoardConstants.FIRST_PIT_INDEX; i <= BoardConstants.LAST_PIT_INDEX; i++) {
            if (i < BoardConstants.LAST_PIT_INDEX / TWO) {
                board.put(i, Pit.createPit(i, Player.FIRST_PLAYER, BoardConstants.INITIAL_STONES_NUMBER));
            } else if (i > BoardConstants.LAST_PIT_INDEX / TWO && i < BoardConstants.SECOND_PLAYER_HOME_INDEX) {
                board.put(i, Pit.createPit(i, Player.SECOND_PLAYER, BoardConstants.INITIAL_STONES_NUMBER));
            } else if (i == BoardConstants.FIRST_PLAYER_HOME_INDEX) {
                final Pit firstPlayerHouse = Pit.createHouse(i, Player.FIRST_PLAYER);
                board.put(i, firstPlayerHouse);
            } else if (i == BoardConstants.SECOND_PLAYER_HOME_INDEX) {
                final Pit secondPlayerHouse = Pit.createHouse(i, Player.SECOND_PLAYER);
                board.put(i, secondPlayerHouse);
            }
        }
        return board;
    }

    public int getId() {
        return id;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Map<Integer, Pit> getBoard() {
        return board;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public void setWinner(final Player winner) {
        this.winner = winner;
    }

    public void setCurrentPlayer(final Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Status getStatus() {
        return status;
    }

    public Player getWinner() {
        return winner;
    }

    public Pit getPlayerHouse(final Player currentPlayer) {
        return currentPlayer == Player.FIRST_PLAYER ? getPitById(BoardConstants.FIRST_PLAYER_HOME_INDEX) : getPitById(
                BoardConstants.SECOND_PLAYER_HOME_INDEX);
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Game)) {
            return false;
        }
        final Game game = (Game) o;
        return id == game.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Game{");
        sb.append("id=")
                .append(id);
        sb.append(", status=")
                .append(status);
        sb.append(", winner=")
                .append(winner);
        sb.append(", currentPlayer=")
                .append(currentPlayer);
        sb.append(", board=")
                .append(board);
        sb.append('}');
        return sb.toString();
    }
}
