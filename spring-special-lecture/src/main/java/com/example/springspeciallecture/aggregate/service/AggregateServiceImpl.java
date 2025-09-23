package com.example.springspeciallecture.aggregate.service;

import com.example.springspeciallecture.aggregate.entity.Aggregate;
import com.example.springspeciallecture.aggregate.repository.AggregateRepository;
import com.example.springspeciallecture.orders.repository.OrdersRepository;
import com.example.springspeciallecture.payments.entity.Payments;
import com.example.springspeciallecture.payments.repository.PaymentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AggregateServiceImpl implements AggregateService {

    private final OrdersRepository ordersRepository;
    private final PaymentsRepository paymentsRepository;
    private final AggregateRepository aggregateRepository;

    @Override
    public void aggregateMonthlySales() {
        YearMonth now = YearMonth.now();
        LocalDateTime start = now.atDay(1).atStartOfDay();
        LocalDateTime end = now.atEndOfMonth().atTime(23, 59, 59);

        // 이번 달 결제 완료된 Payments 조회
        List<Payments> payments = paymentsRepository.findByStatusAndRequestedAtBetween(
                com.example.springspeciallecture.payments.entity.PaymentStatus.SUCCESS,
                start, end
        );

        double totalAmount = payments.stream()
                .mapToDouble(Payments::getAmount)
                .sum();

        System.out.println("[" + now + "] 월별 매출 집계: 총액 = " + totalAmount + "원, 건수 = " + payments.size());
        aggregateRepository.save(
                new Aggregate(
                        totalAmount, payments.size(), LocalDateTime.now()
                )
        );
    }
}
