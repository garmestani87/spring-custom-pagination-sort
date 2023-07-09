package com.garm.pagination.mapper;

import com.garm.pagination.domain.AbstractPersistence;
import com.garm.pagination.model.BaseDto;
import com.garm.pagination.repository.BaseRepository;
import org.mapstruct.MappingTarget;
import org.mapstruct.ObjectFactory;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public interface BaseMapper<E extends AbstractPersistence<L>, D extends BaseDto<L>, L extends Serializable> {

    default BaseRepository<E, L> getRepository() {
        throw new UnsupportedOperationException();
    }

    default Supplier<E> getEntity() {
        throw new UnsupportedOperationException();
    }

    @ObjectFactory
    default E getInstance(D dto) {

        if (Objects.isNull(dto.getId()))
            return getEntity().get();

        Optional<E> entity = getRepository().findById(dto.getId());
        return entity.orElseGet(getEntity());

    }

    D map(E entity);

    E map(D dto);

    E merge(D source, @MappingTarget E target);
}
