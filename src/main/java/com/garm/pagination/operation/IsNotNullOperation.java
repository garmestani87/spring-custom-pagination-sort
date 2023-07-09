package com.garm.pagination.operation;

import com.garm.pagination.dto.Filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class IsNotNullOperation<E> extends AbstractOperator<E> {

    public IsNotNullOperation(Root<E> root, CriteriaBuilder criteriaBuilder) {
        super(root, criteriaBuilder);
    }

    @Override
    public Predicate execute(Filter filter) {
        return criteriaBuilder.isNotNull(relationalGetPath(root, filter.getFieldName()));
    }
}


