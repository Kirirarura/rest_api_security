package com.epam.esm.dao.query;

public enum SortType {

    ASC("ASC"),

    DESC("DESC");

    private final String sortTypeName;

    SortType(String sortTypeName) {
        this.sortTypeName = sortTypeName;
    }

    public String getSortTypeName() {
        return sortTypeName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(sortTypeName);
        return sb.toString();
    }
}
