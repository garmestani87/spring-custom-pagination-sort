package com.garm.pagination.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SortOperation {

    ASC("asc"),
    DESC("desc");

    private String value;

    SortOperation(String value) {
        this.value = value;
    }

    public static SortOperation fromValue(String value) {
        for (SortOperation op : SortOperation.values()) {

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