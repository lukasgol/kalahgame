package lukasgol.kalahgame.uc.create;

import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.dto.GameDto;
import lukasgol.kalahgame.service.GameService;
import lukasgol.kalahgame.uc.GameMapper;
import org.springframework.stereotype.Component;

@Component
public class CreateGameUC {
    private final GameService gameService;
    private final GameMapper gameMapper;

    public CreateGameUC(final GameService gameService, final GameMapper gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    public GameDto createGame() {
        final Game newGame = gameService.createGame();
        return gameMapper.mapGameToGameDto(newGame);
    }
}
