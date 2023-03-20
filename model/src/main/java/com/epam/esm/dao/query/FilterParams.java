package com.epam.esm.dao.query;


public enum FilterParams {
    NAME ("name"),
    TAG_NAME ("tagName"),
    PART_OF_NAME ("partOfName"),
    PART_OF_DESCRIPTION ("partOfDescription"),
    SORT_BY_NAME ("sortByName");

    private final String filterName;

    FilterParams(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterName() {
        return filterName;
    }

    @Override
    public String toString() {
        return "FilterParams{" +
                "filterName='" + filterName + '\'' +
                '}';
    }
}
