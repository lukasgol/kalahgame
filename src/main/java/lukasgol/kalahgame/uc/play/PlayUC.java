package lukasgol.kalahgame.uc.play;

import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.domain.Pit;
import lukasgol.kalahgame.domain.Player;
import lukasgol.kalahgame.domain.Status;
import lukasgol.kalahgame.dto.MoveResponseDto;
import lukasgol.kalahgame.service.GameService;
import lukasgol.kalahgame.uc.GameMapper;
import org.springframework.stereotype.Component;

@Component
public class PlayUC {
    private final MoveValidator moveValidator;
    private final EndGameResolver endGameResolver;
    private final PitMover pitMover;
    private final PlayerTurnChooser playerTurnChooser;
    private final GameService gameService;
    private final GameMapper gameMapper;

    public PlayUC(final MoveValidator moveValidator, final EndGameResolver endGameResolver, final PitMover pitMover,
                  final PlayerTurnChooser playerTurnChooser, final GameService gameService, final GameMapper gameMapper) {
        this.moveValidator = moveValidator;
        this.endGameResolver = endGameResolver;
        this.pitMover = pitMover;
        this.playerTurnChooser = playerTurnChooser;
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    public MoveResponseDto movePit(final Integer gameId, final Integer pitId) {
        final Game game = gameService.getGame(gameId);
        final Pit pit = game.getPitById(pitId);

        moveValidator.validate(game, pitId);

        final Pit currentPitId = pitMover.move(game, pit);
        if (endGameResolver.isGameEnded(game)) {
            endGameResolver.findWinner(game)
                    .ifPresentOrElse(winner -> setWinner(game, winner), () -> setDraw(game));
        } else {
            final Player nextPlayer = playerTurnChooser.choosePlayer(game.getCurrentPlayer(), currentPitId);
            game.setCurrentPlayer(nextPlayer);
        }
        return gameMapper.mapGameToMoveResponse(game);
    }

    private void setDraw(final Game game) {
        game.setStatus(Status.DRAW);
    }

    private void setWinner(final Game game, final Player winner) {
        game.setWinner(winner);
        game.setStatus(Status.WINNER_DETERMINED);
    }
}
