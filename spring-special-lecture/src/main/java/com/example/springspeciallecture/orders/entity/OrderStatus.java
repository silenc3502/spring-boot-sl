package com.example.springspeciallecture.orders.entity;

public enum OrderStatus {
    CREATED,    // 주문 생성됨, 결제 전
    PAID,       // 결제 완료
    CANCELED,   // 주문 취소
    COMPLETED,  // 주문 완료 (배송 완료 등)
    FAILED      // 결제 실패 등
}
