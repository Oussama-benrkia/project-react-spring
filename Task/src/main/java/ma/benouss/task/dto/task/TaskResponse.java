package ma.benouss.task.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.benouss.task.entity.enumuration.Status;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private Long id ;
    private String title;
    private String description;
    private Status status;
    private String createAt;
    private String updateAt;
}
