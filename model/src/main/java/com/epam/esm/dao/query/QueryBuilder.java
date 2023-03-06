package com.epam.esm.dao.query;

import org.springframework.util.MultiValueMap;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public interface QueryBuilder<T> {
    CriteriaQuery<T> createGetQuery(MultiValueMap<String, String> fields, CriteriaBuilder criteriaBuilder);
}
