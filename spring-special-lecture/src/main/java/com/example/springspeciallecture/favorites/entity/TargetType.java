package com.example.springspeciallecture.favorites.entity;

public enum TargetType {
    POST,
    COMMENT;

    public static TargetType from(String value) {
        return TargetType.valueOf(value.toUpperCase());
    }
}

