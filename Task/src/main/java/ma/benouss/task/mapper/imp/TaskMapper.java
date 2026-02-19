package ma.benouss.task.mapper.imp;

import ma.benouss.task.dto.task.TaskRequest;
import ma.benouss.task.dto.task.TaskResponse;
import ma.benouss.task.entity.Task;
import ma.benouss.task.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class TaskMapper implements Mapper<TaskResponse, TaskRequest, Task> {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

    @Override
    public Task toEntity(TaskRequest request) {
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .build();
    }

    @Override
    public TaskResponse toResponse(Task entity) {
        return TaskResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .createAt(entity.getCreatedAt().format(formatter))
                .updateAt(entity.getUpdatedAt().format(formatter))
                .build();
    }


}
