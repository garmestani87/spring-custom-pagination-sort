package com.garm.pagination.mapper;

import com.garm.pagination.domain.AbstractTreePersistence;
import com.garm.pagination.model.BaseTreeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.io.Serializable;

@Mapper(componentModel = "spring")
public interface BaseTreeMapper<E extends AbstractTreePersistence<L, E>, D extends BaseTreeDto<L, D>, L extends Serializable>
        extends BaseMapper<E, D, L> {

    @Mappings({
            @Mapping(target = "parent", qualifiedByName = "mapWithoutParent"),
            @Mapping(target = "children", ignore = true)
    })
    @Override
    E map(D dto);

    @Mappings({
            @Mapping(target = "parent", ignore = true),
            @Mapping(target = "children", ignore = true)
    })
    @Override
    D map(E dto);

    @Named(value = "mapWithoutParent")
    @Mapping(target = "parent", ignore = true)
    E mapWithoutParent(D dto);
}
