package lukasgol.kalahgame.domain;

import java.util.Objects;

public class Pit {
    private static final int ZERO = 0;
    private int id;
    private int stoneNumber;
    private Player owner;
    private boolean isHouse;

    public static Pit createPit(final int id, final Player player, final int stoneNumber) {
        return new Pit(id, stoneNumber, player, false);
    }

    public static Pit createHouse(final int id, final Player player) {
        return new Pit(id, 0, player, true);
    }

    public static Pit createHouse(final int id, final Player player, final int stoneNumber) {
        return new Pit(id, stoneNumber, player, true);
    }

    private Pit(final int id, final int stoneNumber, final Player owner, final boolean isHouse) {
        this.id = id;
        this.stoneNumber = stoneNumber;
        this.isHouse = isHouse;
        this.owner = owner;
    }

    public Integer getStoneNumber() {
        return stoneNumber;
    }

    public void setStoneNumber(final Integer stoneNumber) {
        this.stoneNumber = stoneNumber;
    }

    public void removePits() {
        setStoneNumber(ZERO);
    }

    public boolean isHouse() {
        return isHouse;
    }

    public boolean isDistributable(final Player turn) {
        if (isHouse() && turn.equals(owner)) {
            return true;
        } else if (isHouse() && !turn.equals(owner)) {
            return false;
        }
        return true;
    }

    public Player getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pit)) {
            return false;
        }
        final Pit pit = (Pit) o;
        return id == pit.id && stoneNumber == pit.stoneNumber && isHouse == pit.isHouse && owner == pit.owner;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stoneNumber, owner, isHouse);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pit{");
        sb.append("id=")
                .append(id);
        sb.append(", stoneNumber=")
                .append(stoneNumber);
        sb.append(", owner=")
                .append(owner);
        sb.append(", isHouse=")
                .append(isHouse);
        sb.append('}');
        return sb.toString();
    }
}
