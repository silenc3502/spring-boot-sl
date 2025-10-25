package com.example.springspeciallecture.payments.entity;

import com.example.springspeciallecture.orders.entity.Orders;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 연관된 주문
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders;

    // 결제 수단
    @Enumerated(EnumType.STRING)
    private PaymentMethod method; // CARD, TOSS_PAY 등

    private double amount; // 결제 금액

    @Enumerated(EnumType.STRING)
    private PaymentStatus status; // PENDING, SUCCESS, FAILED

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime requestedAt;

    @UpdateTimestamp
    private LocalDateTime completedAt;

    private String pgTransactionId;

    public Payments(Orders orders,
                    PaymentMethod method,
                    double amount,
                    PaymentStatus status,
                    String pgTransactionId) {
        this.orders = orders;
        this.method = method;
        this.amount = amount;
        this.status = status;
        this.pgTransactionId = pgTransactionId;
    }
}
