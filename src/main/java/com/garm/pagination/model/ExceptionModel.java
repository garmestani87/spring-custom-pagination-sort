package com.garm.pagination.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ExceptionModel {

    private String message;
    private Object stackTrace;
    private String code;

}