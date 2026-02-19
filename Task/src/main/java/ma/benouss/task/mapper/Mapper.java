package ma.benouss.task.mapper;

import ma.benouss.task.common.PageResponse;
import org.springframework.data.domain.Page;

import java.util.function.Function;

public interface Mapper <RES,REQ,E>{
    public E toEntity (REQ request);
    public RES toResponse(E entity);
    default PageResponse<RES> toPages(Page<E> page, Function<E, RES> converter) {
        return PageResponse.<RES>builder()
                .data(page.getContent().stream()
                        .map(converter)
                        .toList())
                .page(page.getNumber())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .totalPage(page.getTotalPages())
                .perPage(page.getSize())
                .totalItems(page.getTotalElements())
                .build();
    }
}
