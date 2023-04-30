package com.zerobase.zerobasestudy.util;

public class Sort {

    private String orderBy;

    public Sort(String columnName, Sort.Direction direction) {
        this.orderBy = columnName + " " + direction.name();
    }

    public enum Direction{
        DESC,
        ASC
    }

    public String getOrderBy() {
        return orderBy;
    }
}
