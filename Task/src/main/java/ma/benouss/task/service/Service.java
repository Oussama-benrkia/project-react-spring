package ma.benouss.task.service;

import ma.benouss.task.common.PageResponse;
import ma.benouss.task.dto.util.ChangeStatusResponse;
import ma.benouss.task.dto.util.SortInput;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface Service<REQ,RES> {
    Optional<RES> add(REQ request);
    Optional<RES> remove(long id);
    Optional<RES> getByID (long id);
    Optional<ChangeStatusResponse<RES>> update(long id, REQ request);
    PageResponse<RES> pagination(String search, int page, int size, SortInput sort);
    default Sort toSort(SortInput sortInput){
        if (sortInput == null || sortInput.fields().isEmpty() ){
            return Sort.by(Sort.Direction.ASC, "createdAt");
        }
        return Sort.by(sortInput.fields().stream().map(f->new Sort.Order(f.direction(), f.field())).toList());
    }
}
