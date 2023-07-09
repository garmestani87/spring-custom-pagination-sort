package com.garm.pagination.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.garm.pagination.enums.FilterOperation;
import com.garm.pagination.utils.LocalDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Filter {

    private String fieldName;

    private FilterOperation operation;

    @JsonDeserialize(using = LocalDeserializer.class)
    private Object value;
}
