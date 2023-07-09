package com.garm.pagination.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PagingRequest {

    private Integer start;
    private Integer size;
    private List<Filter> filters;
    private PagingSort sort;

}
