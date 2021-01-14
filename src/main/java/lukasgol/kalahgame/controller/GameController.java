package lukasgol.kalahgame.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lukasgol.kalahgame.dto.GameDto;
import lukasgol.kalahgame.dto.MoveResponseDto;
import lukasgol.kalahgame.uc.create.CreateGameUC;
import lukasgol.kalahgame.uc.get.GetAllGamesUC;
import lukasgol.kalahgame.uc.play.PlayUC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/games")
@Api(value="KalahGame", description = "Game endpoints ")
public class GameController {
    private final CreateGameUC createGameUC;
    private final PlayUC playUC;
    private final GetAllGamesUC getAllGamesUC;

    public GameController(final CreateGameUC createGameUC, final PlayUC playUC, final GetAllGamesUC getAllGamesUC) {
        this.createGameUC = createGameUC;
        this.playUC = playUC;
        this.getAllGamesUC = getAllGamesUC;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value="Create the new game")
    public GameDto createGame() {
        return createGameUC.createGame();
    }

    @PutMapping(value = "/{gameId}/pits/{pitId}")
    @ApiOperation(value="Make a move")
    public MoveResponseDto move(@PathVariable Integer gameId, @PathVariable Integer pitId) {
        return playUC.movePit(gameId, pitId);
    }

    @GetMapping
    @ApiOperation(value="Get all existing games")
    public List<GameDto> getGames() {
        return getAllGamesUC.getAllGames();
    }
}
