package ma.benouss.task.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.benouss.task.dto.task.TaskRequest;
import ma.benouss.task.dto.task.TaskResponse;
import ma.benouss.task.dto.util.ChangeStatusResponse;
import ma.benouss.task.dto.util.SortInput;
import ma.benouss.task.common.PageResponse;
import ma.benouss.task.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> addTask(@Valid @RequestBody TaskRequest request) {
        return taskService.add(request)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        return taskService.getByID(id)
                .map(ResponseEntity::ok)
                .orElseThrow(); // gestion via GlobalExcepHandler
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChangeStatusResponse<TaskResponse>> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request
    ) {
        return taskService.update(id, request)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.remove(id);
    }

    @GetMapping
    public ResponseEntity<PageResponse<TaskResponse>> listTasks(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) SortInput sort
    ) {
        PageResponse<TaskResponse> response = taskService.pagination(search, page, size, sort);
        return ResponseEntity.ok(response);
    }
}