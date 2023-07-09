package com.garm.pagination.operation;

import com.garm.pagination.dto.Filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GreaterThanOrEqualToOperation<E> extends AbstractOperator<E> {

    public GreaterThanOrEqualToOperation(Root<E> root, CriteriaBuilder criteriaBuilder) {
        super(root, criteriaBuilder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Predicate execute(Filter filter) {
        if (filter.getValue() instanceof Comparable) {
            return criteriaBuilder.greaterThanOrEqualTo(relationalGetPath(root, filter.getFieldName()), ((Comparable) filter.getValue()));
        }
        return criteriaBuilder.greaterThanOrEqualTo(relationalGetPath(root, filter.getFieldName()), filter.getValue().toString());
    }
}

