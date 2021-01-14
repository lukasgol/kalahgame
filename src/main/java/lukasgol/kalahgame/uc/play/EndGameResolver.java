package lukasgol.kalahgame.uc.play;

import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.domain.Pit;
import lukasgol.kalahgame.domain.Player;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EndGameResolver {
    boolean isGameEnded(final Game game) {
        return game.getBoard()
                .values()
                .stream()
                .collect(Collectors.toMap(Pit::getOwner, getStonesInPlay(), Integer::sum))
                .values()
                .stream()
                .anyMatch(stoneCount -> stoneCount.equals(0));
    }

    Optional<Player> findWinner(final Game game) {
        final Map<Player, Integer> results = game.getBoard()
                .values()
                .stream()
                .collect(Collectors.toMap(Pit::getOwner, Pit::getStoneNumber, Integer::sum));
        if (results.get(Player.FIRST_PLAYER)
                .equals(results.get(Player.SECOND_PLAYER))) {
            return Optional.empty();
        }
        return results.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey);
    }

    private Function<Pit, Integer> getStonesInPlay() {
        return pit -> pit.isHouse() ? 0 : pit.getStoneNumber();
    }
}
