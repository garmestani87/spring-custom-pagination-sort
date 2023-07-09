package com.garm.pagination.operation;

import com.garm.pagination.dto.Filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class InOperation<E> extends AbstractOperator<E> {

    public InOperation(Root<E> root, CriteriaBuilder criteriaBuilder) {
        super(root, criteriaBuilder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Predicate execute(Filter filter) {
        return criteriaBuilder.in(relationalGetPath(root, filter.getFieldName())).value(filter.getValue());
    }
}
