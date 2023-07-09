package com.garm.pagination.dto;

import com.garm.pagination.enums.SortOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagingSort {

    private String[] multiField;
    private String fieldName;
    private SortOperation operation;

}
