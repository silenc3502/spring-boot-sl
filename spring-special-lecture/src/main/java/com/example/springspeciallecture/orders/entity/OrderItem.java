package com.example.springspeciallecture.orders.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 연관된 주문
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders;

    private Long gameChipId;
    private Long quantity;
    private Long price;     // 낱개 가격

    public OrderItem(Orders orders, Long gameChipId, Long quantity, Long price) {
        this.orders = orders;
        this.gameChipId = gameChipId;
        this.quantity = quantity;
        this.price = price;
    }
}
