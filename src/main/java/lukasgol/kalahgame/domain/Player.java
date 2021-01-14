package lukasgol.kalahgame.domain;

public enum Player {
    FIRST_PLAYER("Player 1"), SECOND_PLAYER("Player 2");

    private final String name;

    Player(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Player getOpponent() {
        return this == FIRST_PLAYER ? SECOND_PLAYER : FIRST_PLAYER;
    }
}