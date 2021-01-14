package lukasgol.kalahgame.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lukasgol.kalahgame.exception.GameException;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final HttpStatus status;
    private final String error_code;
    private Object value;

    ErrorResponse(final GameException e) {
        this.status = e.getHttpStatus();
        this.error_code = e.getErrorCode();
        this.value = e.getValue();
    }

    ErrorResponse(final HttpStatus status, final String message) {
        this.status = status;
        this.error_code = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getError_code() {
        return error_code;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(final Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ErrorResponse)) {
            return false;
        }
        final ErrorResponse that = (ErrorResponse) o;
        return status == that.status && Objects.equals(error_code, that.error_code) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, error_code, value);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorResponse{");
        sb.append("status=")
                .append(status);
        sb.append(", error_code='")
                .append(error_code)
                .append('\'');
        sb.append(", value=")
                .append(value);
        sb.append('}');
        return sb.toString();
    }
}
