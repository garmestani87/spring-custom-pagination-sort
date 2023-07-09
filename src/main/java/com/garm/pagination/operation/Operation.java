package com.garm.pagination.operation;

import com.garm.pagination.dto.Filter;

import javax.persistence.criteria.Predicate;

public interface Operation {
    default Predicate execute(Filter filter) {
        return null;
    }
}
