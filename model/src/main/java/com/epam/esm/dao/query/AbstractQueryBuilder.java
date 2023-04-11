package com.epam.esm.dao.query;

import org.springframework.util.MultiValueMap;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.epam.esm.dao.query.FilterParams.*;

/**
 * Utility class, designed to build a query to update database and retrieve info from database according to some filters.
 */
public abstract class AbstractQueryBuilder<T> implements QueryBuilder<T> {

    private static final String PERCENT = "%";
    private static final String DESCRIPTION = "description";

    protected List<Predicate> addName(MultiValueMap<String, String> fields, CriteriaBuilder criteriaBuilder, Root<T> root) {
        List<Predicate> restrictions = new ArrayList<>();

        String name = getSingleMultiValueMapParameter(fields, NAME.getFilterName());
        if (name != null) {
            restrictions.add(criteriaBuilder.equal(root.get(NAME.getFilterName()), name));
        }
        return restrictions;
    }

    protected List<Predicate> addPartOfName(MultiValueMap<String, String> fields, CriteriaBuilder criteriaBuilder, Root<T> root) {
        List<Predicate> restrictions = new ArrayList<>();

        String partOfName = getSingleMultiValueMapParameter(fields, PART_OF_NAME.getFilterName());
        if (partOfName != null) {
            restrictions.add(criteriaBuilder.like(root.get(NAME.getFilterName()), PERCENT + partOfName + PERCENT));
        }
        return restrictions;
    }

    protected List<Predicate> addPartOfDescription(MultiValueMap<String, String> fields, CriteriaBuilder criteriaBuilder, Root<T> root) {
        List<Predicate> restrictions = new ArrayList<>();

        String partOfDescription = getSingleMultiValueMapParameter(fields, PART_OF_DESCRIPTION.getFilterName());
        if (partOfDescription != null) {
            restrictions.add(criteriaBuilder.like(root.get(DESCRIPTION), PERCENT + partOfDescription + PERCENT));
        }
        return restrictions;
    }

    protected void addSortByName(MultiValueMap<String, String> fields, CriteriaBuilder criteriaBuilder,
                                 CriteriaQuery<T> criteriaQuery, Root<T> root) {

        String sortType = getSingleMultiValueMapParameter(fields, SORT_BY_NAME.getFilterName());
        if (sortType != null) {
            if (Objects.equals(sortType, SortType.DESC.getSortTypeName())) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(NAME.getFilterName())));
            }
            if (Objects.equals(sortType, SortType.ASC.getSortTypeName())) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(NAME.getFilterName())));
            }
        }
    }

    private String getSingleMultiValueMapParameter(MultiValueMap<String, String> fields, String parameter) {
        if (fields.containsKey(parameter)) {
            return fields.get(parameter).get(0);
        } else {
            return null;
        }
    }
}
