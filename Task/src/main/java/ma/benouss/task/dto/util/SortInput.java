package ma.benouss.task.dto.util;

import java.util.List;

public record SortInput(
        List<SortFieldInput> fields
) {}
