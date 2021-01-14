package lukasgol.kalahgame.uc.get;

import lukasgol.kalahgame.dto.GameDto;
import lukasgol.kalahgame.service.GameService;
import lukasgol.kalahgame.uc.GameMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllGamesUC {
    private final GameService gameService;
    private final GameMapper gameMapper;

    public GetAllGamesUC(final GameService gameService, final GameMapper gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    public List<GameDto> getAllGames() {
        return gameService.getAllGames()
                .stream()
                .map(gameMapper::mapGameToGameDto)
                .collect(Collectors.toList());
    }
}
