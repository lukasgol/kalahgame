package lukasgol.kalahgame.dto;

import java.util.Objects;

public class GameDto {
    private String uri;
    private Integer id;

    public GameDto() {
    }


    public GameDto(final String uri, final Integer id) {
        this.uri = uri;
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GameDto)) {
            return false;
        }
        final GameDto gameDto = (GameDto) o;
        return Objects.equals(uri, gameDto.uri) && Objects.equals(id, gameDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Game{");
        sb.append("uri='")
                .append(uri)
                .append('\'');
        sb.append(", id=")
                .append(id);
        sb.append('}');
        return sb.toString();
    }
}
