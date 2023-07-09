package com.garm.pagination.pagination;

import com.garm.pagination.dto.PagingResponse;
import com.garm.pagination.dto.PagingSort;
import com.garm.pagination.model.BaseDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface Paging<D extends BaseDto> {
    Sort parsingSortRequest(PagingSort sort);

    PagingResponse<List<D>> execute();
}
