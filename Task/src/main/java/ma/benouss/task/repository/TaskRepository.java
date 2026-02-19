package ma.benouss.task.repository;

import ma.benouss.task.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {
    boolean existsByTitleIgnoreCase(String title);

    Page<Task> findByTitleContainingIgnoreCase(String title ,Pageable pageable);
}
