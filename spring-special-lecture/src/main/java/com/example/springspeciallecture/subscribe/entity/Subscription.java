package com.example.springspeciallecture.subscribe.entity;

import com.example.springspeciallecture.account.entity.Account;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id", "followee_id"}))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private Account follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followee_id", nullable = false)
    private Account followee;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public Subscription(Account follower, Account followee) {
        this.follower = follower;
        this.followee = followee;
    }
}

