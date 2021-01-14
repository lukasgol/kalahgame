package lukasgol.kalahgame.service;

import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.exception.GameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

@Service
public class GameService {

    private final AtomicInteger count = new AtomicInteger(1);
    private final Map<Integer, Game> games = new ConcurrentHashMap<>();

    public Game createGame() {
        int id = count.getAndIncrement();
        final Game newGame = new Game(id);
        games.put(id, newGame);
        return newGame;
    }

    public List<Game> getAllGames() {
        return new ArrayList<>(games.values());
    }

    public Game getGame(final int id) {
        return Optional.ofNullable(games.get(id))
                .orElseThrow(throwGameNotFoundException(id));
    }

    private Supplier<GameNotFoundException> throwGameNotFoundException(final Integer gameId) {
        return () -> new GameNotFoundException(gameId);
    }
}
