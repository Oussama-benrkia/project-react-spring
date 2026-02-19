package ma.benouss.task.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private Integer code;
    private String message;
    private String details;
    private Set<String> validationError;
    private Map<String, String> erros;
    private LocalDateTime date;
    private String path;
}
