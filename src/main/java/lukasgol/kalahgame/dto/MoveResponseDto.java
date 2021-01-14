package lukasgol.kalahgame.dto;

import java.util.Map;
import java.util.Objects;

public class MoveResponseDto {
    private String uri;
    private Integer id;
    private Map<Integer, Integer> status;

    public MoveResponseDto(final Integer gameId, final String uri, final Map<Integer, Integer> status) {
        this.id = gameId;
        this.uri = uri;
        this.status = status;
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

    public Map<Integer, Integer> getStatus() {
        return status;
    }

    public void setStatus(final Map<Integer, Integer> status) {
        this.status = status;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MoveResponseDto)) {
            return false;
        }
        final MoveResponseDto that = (MoveResponseDto) o;
        return Objects.equals(uri, that.uri) && Objects.equals(id, that.id) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, id, status);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MoveResponseDto{");
        sb.append("uri='")
                .append(uri)
                .append('\'');
        sb.append(", id=")
                .append(id);
        sb.append(", Status=")
                .append(status);
        sb.append('}');
        return sb.toString();
    }
}
