package ma.benouss.task.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<R> {
    private List<R> data;
    private boolean hasNext;
    private boolean hasPrevious;
    private int page;
    private int totalPage;
    private long totalItems;
    private int perPage;

}
