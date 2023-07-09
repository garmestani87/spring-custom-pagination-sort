package com.garm.pagination.pagination;

import com.garm.pagination.domain.AbstractTreePersistence;
import com.garm.pagination.dto.PagingRequest;
import com.garm.pagination.mapper.BaseTreeMapper;
import com.garm.pagination.model.BaseTreeDto;
import com.garm.pagination.repository.TreePagingBaseRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.StreamUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


public final class PaginationTreeExecutor<E extends AbstractTreePersistence<L, E>, D extends BaseTreeDto<L, D>, L extends Serializable> extends DefaultPaging<E, D, L> {

    private final BaseTreeMapper<E, D, L> mapper;

    public PaginationTreeExecutor(PagingRequest pagingRequest, TreePagingBaseRepository<E, L> repository, BaseTreeMapper<E, D, L> mapper) {
        super(repository, pagingRequest, new BaseSpecification<E>(pagingRequest.getFilters()));
        this.mapper = mapper;
    }

    public PaginationTreeExecutor(PagingRequest pagingRequest, TreePagingBaseRepository<E, L> repository, Specification<E> specification, BaseTreeMapper<E, D, L> mapper) {
        super(repository, pagingRequest, true, new BaseSpecification<E>(pagingRequest.getFilters()).and(specification));
        this.mapper = mapper;
    }

    @Override
    public List<D> convert(List<E> content) {
        return StreamUtils.fromNullable(content).flatMap(es -> es.stream().map(mapper::map)).collect(Collectors.toList());
    }

}
