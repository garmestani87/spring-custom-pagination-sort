package com.garm.pagination.operation;

import com.garm.pagination.dto.Filter;
import com.garm.pagination.utils.SpecialCharacter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ContainsOperation<E> extends AbstractOperator<E> {

    public ContainsOperation(Root<E> root, CriteriaBuilder criteriaBuilder) {
        super(root, criteriaBuilder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Predicate execute(Filter filter) {
        return criteriaBuilder.like(criteriaBuilder.lower(relationalGetPath(root, filter.getFieldName())),
                SpecialCharacter.PERCENT + filter.getValue().toString().toLowerCase() + SpecialCharacter.PERCENT);
    }
}

