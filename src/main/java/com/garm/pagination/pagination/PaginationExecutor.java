package com.garm.pagination.pagination;

import com.garm.pagination.domain.AbstractPersistence;
import com.garm.pagination.repository.BaseRepository;
import com.garm.pagination.dto.PagingRequest;
import com.garm.pagination.mapper.BaseMapper;
import com.garm.pagination.model.BaseDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.StreamUtils;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public final class PaginationExecutor<E extends AbstractPersistence<L>, D extends BaseDto<L>, L extends Serializable> extends DefaultPaging<E, D, L> {

    private final Function<E, D> mapperFunction;

    public PaginationExecutor(PagingRequest pagingRequest, BaseRepository<E, L> repository, BaseMapper<E, D, L> mapper) {
        super(repository, pagingRequest, new BaseSpecification<E>(pagingRequest.getFilters()));
        this.mapperFunction = mapper::map;
    }

    public PaginationExecutor(PagingRequest pagingRequest, BaseRepository<E, L> repository, Specification<E> specification, BaseMapper<E, D, L> mapper) {
        super(repository, pagingRequest, true, new BaseSpecification<E>(pagingRequest.getFilters()).and(specification));
        this.mapperFunction = mapper::map;
    }

    public PaginationExecutor(PagingRequest pagingRequest, BaseRepository<E, L> repository, Specification<E> specification, Function<E, D> mapperFunction) {
        super(repository, pagingRequest, true, new BaseSpecification<E>(pagingRequest.getFilters()).and(specification));
        this.mapperFunction = mapperFunction;
    }

    @Override
    public List<D> convert(List<E> content) {
        return StreamUtils.fromNullable(content).flatMap(es -> es.stream().map(mapperFunction)).collect(Collectors.toList());
    }

}
