package ma.benouss.task.dto.task;

import jakarta.validation.constraints.NotBlank;
import ma.benouss.task.entity.enumuration.Status;
import ma.benouss.task.validation.OnCreate;

public record TaskRequest(
        @NotBlank(groups = OnCreate.class)
        String title,
        String description ,
        Status status
) {
}
