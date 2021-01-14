package lukasgol.kalahgame.uc.play;

import lukasgol.kalahgame.TestHelper;
import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.domain.Pit;
import lukasgol.kalahgame.domain.Player;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PitMoverTest {

    private final PitMover pitMover = new PitMover();

    @Test
    void test() {
        //given
        HashMap<Integer, Pit> endBoard = new HashMap<>(14);
        endBoard.put(1, Pit.createPit(1, Player.FIRST_PLAYER, 0));
        endBoard.put(2, Pit.createPit(2, Player.FIRST_PLAYER, 7));
        endBoard.put(3, Pit.createPit(3, Player.FIRST_PLAYER, 7));
        endBoard.put(4, Pit.createPit(4, Player.FIRST_PLAYER, 7));
        endBoard.put(5, Pit.createPit(5, Player.FIRST_PLAYER, 7));
        endBoard.put(6, Pit.createPit(6, Player.FIRST_PLAYER, 7));
        endBoard.put(7, Pit.createHouse(7, Player.FIRST_PLAYER, 1));
        endBoard.put(8, Pit.createPit(8, Player.SECOND_PLAYER, 6));
        endBoard.put(9, Pit.createPit(9, Player.SECOND_PLAYER, 6));
        endBoard.put(10, Pit.createPit(10, Player.SECOND_PLAYER, 6));
        endBoard.put(11, Pit.createPit(11, Player.SECOND_PLAYER, 6));
        endBoard.put(12, Pit.createPit(12, Player.SECOND_PLAYER, 6));
        endBoard.put(13, Pit.createPit(13, Player.SECOND_PLAYER, 6));
        endBoard.put(14, Pit.createHouse(14, Player.SECOND_PLAYER));
        Game game = TestHelper.createGame();
        final Pit pit = game.getPitById(TestHelper.ID);
        //when
        final Pit endPit = pitMover.move(game, pit);
        //then
        assertThat(game.getBoard()).isEqualTo(endBoard);
        assertThat(endPit).isEqualTo(game.getPitById(7));
    }


    @Test
    void test2() {
        //given
        HashMap<Integer, Pit> endBoard = new HashMap<>(14);
        endBoard.put(1, Pit.createPit(1, Player.FIRST_PLAYER, 6));
        endBoard.put(2, Pit.createPit(2, Player.FIRST_PLAYER, 6));
        endBoard.put(3, Pit.createPit(3, Player.FIRST_PLAYER, 6));
        endBoard.put(4, Pit.createPit(4, Player.FIRST_PLAYER, 0));
        endBoard.put(5, Pit.createPit(5, Player.FIRST_PLAYER, 7));
        endBoard.put(6, Pit.createPit(6, Player.FIRST_PLAYER, 7));
        endBoard.put(7, Pit.createHouse(7, Player.FIRST_PLAYER, 1));
        endBoard.put(8, Pit.createPit(8, Player.SECOND_PLAYER, 7));
        endBoard.put(9, Pit.createPit(9, Player.SECOND_PLAYER, 7));
        endBoard.put(10, Pit.createPit(10, Player.SECOND_PLAYER, 7));
        endBoard.put(11, Pit.createPit(11, Player.SECOND_PLAYER, 6));
        endBoard.put(12, Pit.createPit(12, Player.SECOND_PLAYER, 6));
        endBoard.put(13, Pit.createPit(13, Player.SECOND_PLAYER, 6));
        endBoard.put(14, Pit.createHouse(14, Player.SECOND_PLAYER));
        Game game = TestHelper.createGame();
        final Pit pit = game.getPitById(4);
        //when
        final Pit endPit = pitMover.move(game, pit);
        //then
        assertThat(game.getBoard()).isEqualTo(endBoard);
        assertThat(endPit).isEqualTo(game.getPitById(10));
    }

    @Test
    void test3() {
        //given
        HashMap<Integer, Pit> startBoard = new HashMap<>(14);
        startBoard.put(1, Pit.createPit(1, Player.FIRST_PLAYER, 6));
        startBoard.put(2, Pit.createPit(2, Player.FIRST_PLAYER, 6));
        startBoard.put(3, Pit.createPit(3, Player.FIRST_PLAYER, 4));
        startBoard.put(4, Pit.createPit(4, Player.FIRST_PLAYER, 0));
        startBoard.put(5, Pit.createPit(5, Player.FIRST_PLAYER, 6));
        startBoard.put(6, Pit.createPit(6, Player.FIRST_PLAYER, 6));
        startBoard.put(7, Pit.createHouse(7, Player.FIRST_PLAYER, 10));
        startBoard.put(8, Pit.createPit(8, Player.SECOND_PLAYER, 6));
        startBoard.put(9, Pit.createPit(9, Player.SECOND_PLAYER, 0));
        startBoard.put(10, Pit.createPit(10, Player.SECOND_PLAYER, 2));
        startBoard.put(11, Pit.createPit(11, Player.SECOND_PLAYER, 6));
        startBoard.put(12, Pit.createPit(12, Player.SECOND_PLAYER, 4));
        startBoard.put(13, Pit.createPit(13, Player.SECOND_PLAYER, 6));
        startBoard.put(14, Pit.createHouse(14, Player.SECOND_PLAYER, 10));

        HashMap<Integer, Pit> endBoard = new HashMap<>(14);
        endBoard.put(1, Pit.createPit(1, Player.FIRST_PLAYER, 6));
        endBoard.put(2, Pit.createPit(2, Player.FIRST_PLAYER, 6));
        endBoard.put(3, Pit.createPit(3, Player.FIRST_PLAYER, 4));
        endBoard.put(4, Pit.createPit(4, Player.FIRST_PLAYER, 0));
        endBoard.put(5, Pit.createPit(5, Player.FIRST_PLAYER, 6));
        endBoard.put(6, Pit.createPit(6, Player.FIRST_PLAYER, 6));
        endBoard.put(7, Pit.createHouse(7, Player.FIRST_PLAYER, 10));
        endBoard.put(8, Pit.createPit(8, Player.SECOND_PLAYER, 6));
        endBoard.put(9, Pit.createPit(9, Player.SECOND_PLAYER, 0));
        endBoard.put(10, Pit.createPit(10, Player.SECOND_PLAYER, 0));
        endBoard.put(11, Pit.createPit(11, Player.SECOND_PLAYER, 7));
        endBoard.put(12, Pit.createPit(12, Player.SECOND_PLAYER, 5));
        endBoard.put(13, Pit.createPit(13, Player.SECOND_PLAYER, 6));
        endBoard.put(14, Pit.createHouse(14, Player.SECOND_PLAYER, 10));

        Game game = TestHelper.createGame(startBoard);
        final Pit pit = game.getPitById(10);
        //when
        final Pit endPit = pitMover.move(game, pit);
        //then
        assertThat(game.getBoard()).isEqualTo(endBoard);
        assertThat(endPit).isEqualTo(game.getPitById(12));
    }

    @Test
    void test4() {
        //given
        HashMap<Integer, Pit> startBoard = new HashMap<>(14);
        startBoard.put(1, Pit.createPit(1, Player.FIRST_PLAYER, 6));
        startBoard.put(2, Pit.createPit(2, Player.FIRST_PLAYER, 6));
        startBoard.put(3, Pit.createPit(3, Player.FIRST_PLAYER, 4));
        startBoard.put(4, Pit.createPit(4, Player.FIRST_PLAYER, 0));
        startBoard.put(5, Pit.createPit(5, Player.FIRST_PLAYER, 6));
        startBoard.put(6, Pit.createPit(6, Player.FIRST_PLAYER, 6));
        startBoard.put(7, Pit.createHouse(7, Player.FIRST_PLAYER, 10));
        startBoard.put(8, Pit.createPit(8, Player.SECOND_PLAYER, 6));
        startBoard.put(9, Pit.createPit(9, Player.SECOND_PLAYER, 0));
        startBoard.put(10, Pit.createPit(10, Player.SECOND_PLAYER, 2));
        startBoard.put(11, Pit.createPit(11, Player.SECOND_PLAYER, 6));
        startBoard.put(12, Pit.createPit(12, Player.SECOND_PLAYER, 4));
        startBoard.put(13, Pit.createPit(13, Player.SECOND_PLAYER, 6));
        startBoard.put(14, Pit.createHouse(14, Player.SECOND_PLAYER, 10));

        HashMap<Integer, Pit> endBoard = new HashMap<>(14);
        endBoard.put(1, Pit.createPit(1, Player.FIRST_PLAYER, 7));
        endBoard.put(2, Pit.createPit(2, Player.FIRST_PLAYER, 7));
        endBoard.put(3, Pit.createPit(3, Player.FIRST_PLAYER, 5));
        endBoard.put(4, Pit.createPit(4, Player.FIRST_PLAYER, 0));
        endBoard.put(5, Pit.createPit(5, Player.FIRST_PLAYER, 6));
        endBoard.put(6, Pit.createPit(6, Player.FIRST_PLAYER, 6));
        endBoard.put(7, Pit.createHouse(7, Player.FIRST_PLAYER, 10));
        endBoard.put(8, Pit.createPit(8, Player.SECOND_PLAYER, 6));
        endBoard.put(9, Pit.createPit(9, Player.SECOND_PLAYER, 0));
        endBoard.put(10, Pit.createPit(10, Player.SECOND_PLAYER, 2));
        endBoard.put(11, Pit.createPit(11, Player.SECOND_PLAYER, 0));
        endBoard.put(12, Pit.createPit(12, Player.SECOND_PLAYER, 5));
        endBoard.put(13, Pit.createPit(13, Player.SECOND_PLAYER, 7));
        endBoard.put(14, Pit.createHouse(14, Player.SECOND_PLAYER, 11));

        Game game = TestHelper.createGame(startBoard);
        final Pit pit = game.getPitById(11);
        game.setCurrentPlayer(Player.SECOND_PLAYER);
        //when
        final Pit endPit = pitMover.move(game, pit);
        //then
        assertThat(game.getBoard()).isEqualTo(endBoard);
        assertThat(endPit).isEqualTo(game.getPitById(3));
    }

    @Test
    void test5() {
        //given
        HashMap<Integer, Pit> startBoard = new HashMap<>(14);
        startBoard.put(1, Pit.createPit(1, Player.FIRST_PLAYER, 3));
        startBoard.put(2, Pit.createPit(2, Player.FIRST_PLAYER, 6));
        startBoard.put(3, Pit.createPit(3, Player.FIRST_PLAYER, 4));
        startBoard.put(4, Pit.createPit(4, Player.FIRST_PLAYER, 0));
        startBoard.put(5, Pit.createPit(5, Player.FIRST_PLAYER, 6));
        startBoard.put(6, Pit.createPit(6, Player.FIRST_PLAYER, 6));
        startBoard.put(7, Pit.createHouse(7, Player.FIRST_PLAYER, 10));
        startBoard.put(8, Pit.createPit(8, Player.SECOND_PLAYER, 6));
        startBoard.put(9, Pit.createPit(9, Player.SECOND_PLAYER, 0));
        startBoard.put(10, Pit.createPit(10, Player.SECOND_PLAYER, 2));
        startBoard.put(11, Pit.createPit(11, Player.SECOND_PLAYER, 6));
        startBoard.put(12, Pit.createPit(12, Player.SECOND_PLAYER, 4));
        startBoard.put(13, Pit.createPit(13, Player.SECOND_PLAYER, 6));
        startBoard.put(14, Pit.createHouse(14, Player.SECOND_PLAYER, 10));

        HashMap<Integer, Pit> endBoard = new HashMap<>(14);
        endBoard.put(1, Pit.createPit(1, Player.FIRST_PLAYER, 0));
        endBoard.put(2, Pit.createPit(2, Player.FIRST_PLAYER, 7));
        endBoard.put(3, Pit.createPit(3, Player.FIRST_PLAYER, 5));
        endBoard.put(4, Pit.createPit(4, Player.FIRST_PLAYER, 0));
        endBoard.put(5, Pit.createPit(5, Player.FIRST_PLAYER, 6));
        endBoard.put(6, Pit.createPit(6, Player.FIRST_PLAYER, 6));
        endBoard.put(7, Pit.createHouse(7, Player.FIRST_PLAYER, 13));
        endBoard.put(8, Pit.createPit(8, Player.SECOND_PLAYER, 6));
        endBoard.put(9, Pit.createPit(9, Player.SECOND_PLAYER, 0));
        endBoard.put(10, Pit.createPit(10, Player.SECOND_PLAYER, 0));
        endBoard.put(11, Pit.createPit(11, Player.SECOND_PLAYER, 6));
        endBoard.put(12, Pit.createPit(12, Player.SECOND_PLAYER, 4));
        endBoard.put(13, Pit.createPit(13, Player.SECOND_PLAYER, 6));
        endBoard.put(14, Pit.createHouse(14, Player.SECOND_PLAYER, 10));

        Game game = TestHelper.createGame(startBoard);
        final Pit pit = game.getPitById(1);
        //when
        final Pit endPit = pitMover.move(game, pit);
        //then
        assertThat(game.getBoard()).isEqualTo(endBoard);
        assertThat(endPit).isEqualTo(game.getPitById(4));
    }
}