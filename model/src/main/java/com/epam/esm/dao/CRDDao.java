package com.epam.esm.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

public interface CRDDao<T> {

    T insert(T item);

    Optional<T> findById(Long id);

    List<T> findAll(Pageable pageable);

    void removeById(Long id);

    List<T> findWithFilters(MultiValueMap<String, String> fields, Pageable pageable);

}
