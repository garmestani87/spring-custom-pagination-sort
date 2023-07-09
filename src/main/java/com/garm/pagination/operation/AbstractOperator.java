package com.garm.pagination.operation;

import com.garm.pagination.utils.SpecialCharacter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class AbstractOperator<E> implements Operation {

    protected Root<E> root;
    protected CriteriaBuilder criteriaBuilder;

    public AbstractOperator(Root<E> root, CriteriaBuilder criteriaBuilder) {
        this.root = root;
        this.criteriaBuilder = criteriaBuilder;
    }

    protected LocalDate convertStrToDate(String date) {
        String[] strDate = date.split(SpecialCharacter.DASH);
        return LocalDate.of(
                Integer.parseInt(strDate[0]),   // year
                Integer.parseInt(strDate[1]),   // month
                Integer.parseInt(strDate[2])    // day
        );
    }

    protected Path relationalGetPath(Root<E> root, String stringPath) {
        if (!stringPath.contains(SpecialCharacter.DOT)) {
            return root.get(stringPath);
        }
        final String[] paths = stringPath.split(SpecialCharacter.BACK_DOT);
        Join<Object, Object> join = root.join(paths[0]);
        final int SIZE = paths.length - 1;
        for (int i = 1; i < SIZE; i++) {
            join = join.join(paths[i]);
        }
        return join.get(paths[SIZE]);
    }
}
