package com.garm.pagination.operation;

import com.garm.pagination.dto.Filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GreaterThanOperator<E> extends AbstractOperator<E> {

    public GreaterThanOperator(Root<E> root, CriteriaBuilder criteriaBuilder) {
        super(root, criteriaBuilder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Predicate execute(Filter filter) {
        if (filter.getValue() instanceof Comparable) {
            return criteriaBuilder.greaterThan(relationalGetPath(root, filter.getFieldName()), ((Comparable) filter.getValue()));
        }
        return criteriaBuilder.greaterThan(relationalGetPath(root, filter.getFieldName()), filter.getValue().toString());
    }
}

