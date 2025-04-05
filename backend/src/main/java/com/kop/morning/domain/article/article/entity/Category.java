package com.kop.morning.domain.article.article.entity;

public enum Category {
    FREE("자유"),
    NEWS("뉴스");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    public String toString() {
        return category;
    }
}
