package com.garm.pagination.repository;

import com.garm.pagination.domain.AbstractPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;


public interface BaseRepository<T extends AbstractPersistence<L>, L extends Serializable> extends JpaRepository<T, L>, JpaSpecificationExecutor<T> {
}
