package com.garm.pagination.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractTreePersistence<L extends Serializable, E extends AbstractTreePersistence<L, E>> extends AbstractPersistence<L> {

    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    protected E parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<E> children = new ArrayList<>();

    @Column(name = "PATH")
    protected String path;

    @Transient
    public Boolean isLeaf() {
        return Objects.isNull(getChildren()) || getChildren().isEmpty() ? Boolean.TRUE : Boolean.FALSE;
    }
}


