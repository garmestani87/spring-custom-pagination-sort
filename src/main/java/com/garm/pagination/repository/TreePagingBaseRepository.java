package com.garm.pagination.repository;

import com.garm.pagination.domain.AbstractTreePersistence;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@NoRepositoryBean
public interface TreePagingBaseRepository<T extends AbstractTreePersistence<L, T>, L extends Serializable> extends BaseRepository<T, L> {

    List<T> findAllByPathContains(String path);

    List<T> findAllByPathContainsAndPathNot(String path, String notPath);

    default List<T> findChildren(L id) {
        if (Objects.isNull(id)) {
            return this.findRoots();
        }
        return this.findRealChildren(id);
    }

    @Query("select t from #{#entityName} t where t.parent.id = ?1")
    List<T> findRealChildren(L id);

    @Query("select t from #{#entityName} t where t.parent IS NULL")
    List<T> findRoots();

    default List<T> findListTree(L id) {
        Optional<T> t = findById(id);
        return t.map(value -> findAllByPathContains(value.getPath())).orElse(null);
    }

    default List<T> findListSubTree(L id) {
        Optional<T> t = findById(id);
        return t.map(value -> this.findAllByPathContainsAndPathNot(value.getPath(), value.getPath())).orElse(null);
    }

}
