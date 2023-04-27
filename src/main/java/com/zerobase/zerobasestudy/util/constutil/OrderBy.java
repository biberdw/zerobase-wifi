package com.zerobase.zerobasestudy.util.constutil;

public enum OrderBy {
    ID_DESC("ID DESC") , ID_ASC("ID ASC");

    private final String orderBy;

    OrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderBy(){
        return orderBy;
    }
}
