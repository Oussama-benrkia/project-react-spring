package ma.benouss.task.service.imp;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import ma.benouss.task.common.PageResponse;
import ma.benouss.task.dto.task.TaskRequest;
import ma.benouss.task.dto.task.TaskResponse;
import ma.benouss.task.dto.util.ChangeStatusResponse;
import ma.benouss.task.dto.util.SortInput;
import ma.benouss.task.entity.Task;
import ma.benouss.task.entity.enumuration.Status;
import ma.benouss.task.mapper.imp.TaskMapper;
import ma.benouss.task.repository.TaskRepository;
import ma.benouss.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TaskServiceImpl implements TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository repository;
    private final TaskMapper mapper;

    public TaskServiceImpl(TaskRepository repository, TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<TaskResponse> add(TaskRequest request) {
        log.info("Adding task with title: {}", request.title());

        if (repository.existsByTitleIgnoreCase(request.title())) {
            log.warn("Task with title '{}' already exists", request.title());
            throw new EntityExistsException("Task with name " + request.title() + " already exists");
        }

        Task task = mapper.toEntity(request);
        task.setStatus(Status.NEW);

        Task saved = repository.save(task);
        log.info("Task added with ID: {}", saved.getId());

        return Optional.ofNullable(mapper.toResponse(saved));
    }

    @Override
    public Optional<TaskResponse> remove(long id) {
        Task task = getTaskById(id);
        log.info("Removing task with ID: {}", id);
        repository.delete(task);
        return Optional.empty();
    }

    @Override
    public Optional<TaskResponse> getByID(long id) {
        Task task = getTaskById(id);
        log.info("Fetching task with ID: {}", id);
        return Optional.ofNullable(mapper.toResponse(task));
    }

    @Override
    public Optional<ChangeStatusResponse<TaskResponse>> update(long id, TaskRequest request) {
        Task task = getTaskById(id);
        boolean updated = false;

        // Update title
        if (!request.title().isEmpty() && !request.title().equalsIgnoreCase(task.getTitle())) {
            if (repository.existsByTitleIgnoreCase(request.title())) {
                log.warn("Cannot update task ID {}: title '{}' already exists", id, request.title());
                throw new EntityExistsException("Task with name " + request.title() + " already exists");
            }
            task.setTitle(request.title());
            updated = true;
        }

        // Update description
        if (!request.description().isEmpty() && !request.description().equals(task.getDescription())) {
            task.setDescription(request.description());
            updated = true;
        }

        // Update status
        if (request.status() != null && request.status() != task.getStatus()) {
            task.setStatus(request.status());
            updated = true;
        }

        if (updated) {
            task = repository.save(task);
            log.info("Task ID {} updated successfully", id);
        } else {
            log.info("Task ID {}: no changes detected", id);
        }

        return Optional.ofNullable(ChangeStatusResponse.buildResponse(mapper.toResponse(task), updated));
    }

    @Override
    public PageResponse<TaskResponse> pagination(String search, int page, int size, SortInput sort) {
        Pageable pageable = PageRequest.of(page, size, toSort(sort));
        Page<Task> tasks;

        if (search != null && !search.isEmpty()) {
            log.info("Searching tasks with keyword: {}", search);
            tasks = repository.findByTitleContainingIgnoreCase(search, pageable);
        } else {
            tasks = repository.findAll(pageable);
        }

        return mapper.toPages(tasks, mapper::toResponse);
    }

    // Helper method to fetch task by ID
    private Task getTaskById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Task with ID {} not found", id);
                    return new EntityNotFoundException("Task not found");
                });
    }

}