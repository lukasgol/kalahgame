package lukasgol.kalahgame.uc.play;

import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.domain.Pit;
import lukasgol.kalahgame.domain.Player;
import lukasgol.kalahgame.domain.Status;
import lukasgol.kalahgame.service.GameService;
import lukasgol.kalahgame.uc.GameMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static lukasgol.kalahgame.TestHelper.ID;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class PlayUCTest {

    private PlayUC playUC;

    private MoveValidator moveValidator = mock(MoveValidator.class);
    private EndGameResolver endGameResolver = mock(EndGameResolver.class);
    private PitMover pitMover = mock(PitMover.class);
    private PlayerTurnChooser playerTurnChooser = mock(PlayerTurnChooser.class);
    private GameService gameService = mock(GameService.class);
    private GameMapper gameMapper = mock(GameMapper.class);

    @BeforeEach
    void setUp() {
        playUC = new PlayUC(moveValidator, endGameResolver, pitMover, playerTurnChooser, gameService, gameMapper);
    }

    @Test
    void whenGameIsNotEndedShouldChooseNewPlayer() {
        //given
        final Game mockedGame = mock(Game.class);
        final Pit startPit = mock(Pit.class);
        final Pit ednPit = mock(Pit.class);
        when(mockedGame.getCurrentPlayer()).thenReturn(Player.FIRST_PLAYER);
        final int startPitId = 2;
        when(mockedGame.getPitById(startPitId)).thenReturn(startPit);
        when(gameService.getGame(ID)).thenReturn(mockedGame);
        when(pitMover.move(mockedGame, startPit)).thenReturn(ednPit);
        when(endGameResolver.isGameEnded(mockedGame)).thenReturn(false);
        when(playerTurnChooser.choosePlayer(Player.FIRST_PLAYER, ednPit)).thenReturn(Player.SECOND_PLAYER);
        //when
        playUC.movePit(ID, startPitId);
        //then
        verify(moveValidator).validate(mockedGame, startPitId);
        verify(gameService).getGame(ID);
        verify(pitMover).move(mockedGame, startPit);
        verify(endGameResolver).isGameEnded(mockedGame);
        verify(endGameResolver, times(0)).findWinner(mockedGame);
        verifyNoMoreInteractions(endGameResolver);
        verify(playerTurnChooser).choosePlayer(Player.FIRST_PLAYER, ednPit);
        verify(mockedGame).setCurrentPlayer(Player.SECOND_PLAYER);
        verify(gameMapper).mapGameToMoveResponse(mockedGame);
    }

    @Test
    void whenGameIsEndedShouldChooseAndSetWinner() {
        //given
        final Game mockedGame = mock(Game.class);
        final Pit startPit = mock(Pit.class);
        final Pit ednPit = mock(Pit.class);
        when(mockedGame.getCurrentPlayer()).thenReturn(Player.FIRST_PLAYER);
        final int startPitId = 2;
        when(mockedGame.getPitById(startPitId)).thenReturn(startPit);
        when(gameService.getGame(ID)).thenReturn(mockedGame);
        when(pitMover.move(mockedGame, startPit)).thenReturn(ednPit);
        when(endGameResolver.isGameEnded(mockedGame)).thenReturn(true);
        when(endGameResolver.findWinner(mockedGame)).thenReturn(Optional.of(Player.FIRST_PLAYER));
        //when
        playUC.movePit(ID, startPitId);
        //then
        verify(moveValidator).validate(mockedGame, startPitId);
        verify(gameService).getGame(ID);
        verify(pitMover).move(mockedGame, startPit);
        verify(endGameResolver).isGameEnded(mockedGame);
        verify(endGameResolver).findWinner(mockedGame);
        verifyNoInteractions(playerTurnChooser);
        verify(gameMapper).mapGameToMoveResponse(mockedGame);
        verify(mockedGame).setWinner(Player.FIRST_PLAYER);
        verify(mockedGame).setStatus(Status.WINNER_DETERMINED);
    }

    @Test
    void whenGameIsEndedAndNoWinnerShouldSetGameStatusToDraw() {
        //given
        final Game mockedGame = mock(Game.class);
        final Pit startPit = mock(Pit.class);
        final Pit ednPit = mock(Pit.class);
        when(mockedGame.getCurrentPlayer()).thenReturn(Player.FIRST_PLAYER);
        final int startPitId = 2;
        when(mockedGame.getPitById(startPitId)).thenReturn(startPit);
        when(gameService.getGame(ID)).thenReturn(mockedGame);
        when(pitMover.move(mockedGame, startPit)).thenReturn(ednPit);
        when(endGameResolver.isGameEnded(mockedGame)).thenReturn(true);
        when(endGameResolver.findWinner(mockedGame)).thenReturn(Optional.empty());
        //when
        playUC.movePit(ID, startPitId);
        //then
        verify(moveValidator).validate(mockedGame, startPitId);
        verify(gameService).getGame(ID);
        verify(pitMover).move(mockedGame, startPit);
        verify(endGameResolver).isGameEnded(mockedGame);
        verify(endGameResolver).findWinner(mockedGame);
        verifyNoInteractions(playerTurnChooser);
        verify(gameMapper).mapGameToMoveResponse(mockedGame);
        verify(mockedGame).setStatus(Status.DRAW);
    }
}