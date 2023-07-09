package com.garm.pagination.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PagingResponse<D> {

    private int start;
    private int size;
    private int count;
    private D data;

}
