package com.garm.pagination.operation;

import com.garm.pagination.dto.Filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class EqualOperation<E> extends AbstractOperator<E> {

    public EqualOperation(Root<E> root, CriteriaBuilder criteriaBuilder) {
        super(root, criteriaBuilder);
    }

    @Override
    public Predicate execute(Filter filter) {
        return criteriaBuilder.equal(relationalGetPath(root, filter.getFieldName()), filter.getValue());
    }
}
