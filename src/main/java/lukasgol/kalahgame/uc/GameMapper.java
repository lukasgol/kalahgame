package lukasgol.kalahgame.uc;

import lukasgol.kalahgame.domain.Game;
import lukasgol.kalahgame.dto.GameDto;
import lukasgol.kalahgame.dto.MoveResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GameMapper {
    @Value("${server.url}")
    String serverUri;

    public GameDto mapGameToGameDto(final Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setId(game.getId());
        final String uri = UriComponentsBuilder.fromHttpUrl(serverUri)
                .path("/games/{id}")
                .buildAndExpand(game.getId())
                .toString();
        gameDto.setUri(uri);
        return gameDto;
    }

    public MoveResponseDto mapGameToMoveResponse(final Game game) {
        final Map<Integer, Integer> status = game.getBoard()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, integerPitEntry -> integerPitEntry.getValue()
                        .getStoneNumber()));
        final String uri = UriComponentsBuilder.fromHttpUrl(serverUri)
                .path("/games/{id}")
                .buildAndExpand(game.getId())
                .toString();
        return new MoveResponseDto(game.getId(), uri, status);
    }
}
