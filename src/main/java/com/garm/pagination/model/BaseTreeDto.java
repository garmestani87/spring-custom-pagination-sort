package com.garm.pagination.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseTreeDto<L extends Serializable, T extends BaseTreeDto<L, T>> extends BaseDto<L> {

    protected T parent;

    protected List<T> children = new ArrayList<>();

    protected String path;
}
