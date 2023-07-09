package com.garm.pagination.operation;

import com.garm.pagination.dto.Filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class IsNullOperation<E> extends AbstractOperator<E> {

    public IsNullOperation(Root<E> root, CriteriaBuilder criteriaBuilder) {
        super(root, criteriaBuilder);
    }

    @Override
    public Predicate execute(Filter filter) {
        return criteriaBuilder.isNull(relationalGetPath(root, filter.getFieldName()));
    }
}
