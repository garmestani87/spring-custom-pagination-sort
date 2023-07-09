package com.garm.pagination.pagination;

import com.garm.pagination.dto.Filter;
import com.garm.pagination.enums.FilterOperation;
import com.garm.pagination.operation.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;


@AllArgsConstructor
public class BaseSpecification<E> implements Specification<E> {

    private final List<Filter> filters;

    @Override
    public Predicate toPredicate(Root<E> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
        Map<FilterOperation, Operation> operations = createOperation(root, criteriaBuilder);
        Optional.ofNullable(filters).orElse(Collections.emptyList())
                .forEach(filter -> predicates.add(operations.get(filter.getOperation()).execute(filter)));

        criteriaQuery.distinct(true);
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }


    public Map<FilterOperation, Operation> createOperation(Root<E> root, CriteriaBuilder criteriaBuilder) {
        Map<FilterOperation, Operation> operations = new HashMap<>();
        operations.put(FilterOperation.EQUAL, new EqualOperation<E>(root, criteriaBuilder));
        operations.put(FilterOperation.NOT_EQUAL, new NotEqualOperation<E>(root, criteriaBuilder));
        operations.put(FilterOperation.GREATER_THAN, new GreaterThanOperator<>(root, criteriaBuilder));
        operations.put(FilterOperation.GREATER_THAN_OR_EQUAL_TO, new GreaterThanOrEqualToOperation<>(root, criteriaBuilder));
        operations.put(FilterOperation.LESS_THAN, new LessThanOperator<>(root, criteriaBuilder));
        operations.put(FilterOperation.LESS_THAN_OR_EQUAL_TO, new LessThanOrEqualToOperation<>(root, criteriaBuilder));
        operations.put(FilterOperation.IN, new InOperation<>(root, criteriaBuilder));
        operations.put(FilterOperation.NOT_IN, new NotInOperation<>(root, criteriaBuilder));
        operations.put(FilterOperation.IS_NULL, new IsNullOperation<>(root, criteriaBuilder));
        operations.put(FilterOperation.IS_NOT_NULL, new IsNotNullOperation<>(root, criteriaBuilder));
        operations.put(FilterOperation.CONTAINS, new ContainsOperation<>(root, criteriaBuilder));
        return operations;
    }

}

