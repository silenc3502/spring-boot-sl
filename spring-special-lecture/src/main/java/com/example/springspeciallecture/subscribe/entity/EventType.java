package com.example.springspeciallecture.subscribe.entity;

public enum EventType {
    SUBSCRIBE,           // 구독 등록
    UNSUBSCRIBE;         // 구독 취소

    public static EventType from(String value) {
        return EventType.valueOf(value.toUpperCase());
    }
}
