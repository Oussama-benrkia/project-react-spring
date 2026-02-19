package ma.benouss.task.dto.util;

import org.springframework.data.domain.Sort;

public record SortFieldInput(
        String field,
        Sort.Direction direction
) {
}
