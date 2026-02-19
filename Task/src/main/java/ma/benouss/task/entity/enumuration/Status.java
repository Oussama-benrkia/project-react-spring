package ma.benouss.task.entity.enumuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    NEW("New"),
    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    FAILED("Failed"),
    FINISHED("Finished"),
    CLOSED("Closed"),
    CANCELLED("Cancelled");
    private String name;

}
