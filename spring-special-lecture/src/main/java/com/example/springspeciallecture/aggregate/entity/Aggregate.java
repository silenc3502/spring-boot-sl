package com.example.springspeciallecture.aggregate.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.YearMonth;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Aggregate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalAmount; // 총 매출
    private int totalCount;     // 결제 건수

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @CreationTimestamp
    private LocalDateTime aggregatedAt; // 집계 수행 시간

    public Aggregate(double totalAmount, int totalCount, LocalDateTime aggregatedAt) {
        this.totalAmount = totalAmount;
        this.totalCount = totalCount;
        this.aggregatedAt = aggregatedAt;
    }
}
