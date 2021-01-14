package lukasgol.kalahgame.controller;

import lukasgol.kalahgame.uc.create.CreateGameUC;
import lukasgol.kalahgame.uc.get.GetAllGamesUC;
import lukasgol.kalahgame.uc.play.PlayUC;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static lukasgol.kalahgame.TestHelper.createGameDto;
import static lukasgol.kalahgame.TestHelper.createMoveResponse;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class GameControllerTest {

    private final static String GAMES_URI = "/games";
    private final static String PLAY_URI = "/games/{gameId}/pits/{pitId}";
    private static final Integer PIT_ID = 3;
    private static final Integer GAME_ID = 1;
    private static final String EXPECTED_URI = "http://localhost:8080/games/1";
    private static final int EXPECTED_ID = 1;

    private MockMvc mockMvc;
    @MockBean
    private CreateGameUC createGameUC;
    @MockBean
    private PlayUC playUC;
    @MockBean
    private GetAllGamesUC getAllGamesUC;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new GameController(createGameUC, playUC, getAllGamesUC))
                .build();
    }

    @Test
    public void shouldResolveUriFoRCreatingGame() throws Exception {
        //given
        when(createGameUC.createGame()).thenReturn(createGameDto());

        //when
        mockMvc.perform(post(GAMES_URI))
                //then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.uri").value(EXPECTED_URI))
                .andExpect(jsonPath("$.id").value(EXPECTED_ID));
    }

    @Test
    public void shouldResolveUriForGettingAllGames() throws Exception {
        //given
        when(getAllGamesUC.getAllGames()).thenReturn(List.of(createGameDto()));

        //when
        mockMvc.perform(get(GAMES_URI))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].uri", is(EXPECTED_URI)));
    }

    @Test
    public void shouldResolveUriForMakingMove() throws Exception {
        //given
        when(playUC.movePit(GAME_ID, PIT_ID)).thenReturn(createMoveResponse());

        //when
        mockMvc.perform(put(PLAY_URI, GAME_ID, PIT_ID))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uri").value(EXPECTED_URI))
                .andExpect(jsonPath("$.id").value(EXPECTED_ID))
                .andExpect(jsonPath("$.status").isMap())
                .andExpect(jsonPath("$.status.1", is(0)))
                .andExpect(jsonPath("$.status.2", is(6)));

    }


}