package com.garm.pagination.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FilterOperation {

    EQUAL("eq"),
    NOT_EQUAL("neq"),
    GREATER_THAN("gt"),
    GREATER_THAN_OR_EQUAL_TO("gte"),
    LESS_THAN("lt"),
    LESS_THAN_OR_EQUAL_TO("lte"),
    IN("in"),
    NOT_IN("nin"),
    IS_NULL("is_null"),
    IS_NOT_NULL("is_not_null"),
    CONTAINS("like");

    private String value;

    FilterOperation(String value) {
        this.value = value;
    }

    public static FilterOperation fromValue(String value) {
        for (FilterOperation op : FilterOperation.values()) {

            if (String.valueOf(op.value).equalsIgnoreCase(value)) {
                return op;
            }
        }
        return null;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

}