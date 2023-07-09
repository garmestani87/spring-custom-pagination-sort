package com.garm.pagination.pagination;

import com.garm.pagination.domain.AbstractPersistence;
import com.garm.pagination.dto.PagingRequest;
import com.garm.pagination.dto.PagingResponse;
import com.garm.pagination.dto.PagingSort;
import com.garm.pagination.model.BaseDto;
import com.garm.pagination.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class DefaultPaging<E extends AbstractPersistence<L>, D extends BaseDto, L extends Serializable> implements Paging<D> {

    private final PagingRequest pagingRequest;
    private final BaseRepository<E, L> repository;
    private boolean isCompositeSpecification;
    private BaseSpecification<E> specification;
    private Specification<E> compositeSpecification;


    protected DefaultPaging(BaseRepository<E, L> repository, PagingRequest pagingRequest, BaseSpecification<E> specification) {
        this.repository = repository;
        this.pagingRequest = pagingRequest;
        this.specification = specification;
    }

    protected DefaultPaging(BaseRepository<E, L> repository, PagingRequest pagingRequest, Boolean isCompositeSpecification, Specification<E> compositeSpecification) {
        this.repository = repository;
        this.pagingRequest = pagingRequest;
        this.isCompositeSpecification = isCompositeSpecification;
        this.compositeSpecification = compositeSpecification;
    }

    @Override
    public final Sort parsingSortRequest(PagingSort sort) {
        if (Objects.isNull(sort)) return null;
        if (Objects.isNull(sort.getMultiField())) {
            String[] fieldNames = new String[]{sort.getFieldName()};
            sort.setMultiField(fieldNames);
        }
        return Sort.by(Sort.Direction.valueOf(sort.getOperation().name()), sort.getMultiField());
    }

    @Override
    public final PagingResponse<List<D>> execute() {
        Sort sort = this.parsingSortRequest(pagingRequest.getSort());
        Pageable pageable = Objects.isNull(sort)
                ? PageRequest.of(this.pagingRequest.getStart() / this.pagingRequest.getSize(), this.pagingRequest.getSize())
                : PageRequest.of(this.pagingRequest.getStart() / this.pagingRequest.getSize(), this.pagingRequest.getSize(), sort);

        Page<E> response = !isCompositeSpecification
                ? repository.findAll(specification, pageable)
                : repository.findAll(compositeSpecification, pageable);

        PagingResponse<List<D>> pagingResponse = new PagingResponse<>();
        pagingResponse.setCount(response.getTotalPages());
        pagingResponse.setSize((int) response.getTotalElements());
        pagingResponse.setStart(this.pagingRequest.getStart());
        pagingResponse.setData(convert(response.getContent()));
        return pagingResponse;
    }

    public abstract List<D> convert(List<E> content);

}
