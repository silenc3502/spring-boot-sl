package com.example.springspeciallecture.concurrent;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        excludeAutoConfiguration = R2dbcAutoConfiguration.class,
        properties = {
                "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
                "spring.datasource.url=jdbc:h2:mem:testdb;MODE=MySQL",
                "spring.jpa.hibernate.ddl-auto=create-drop"
        }
)
@Import(StockConcurrencyTest.TestJpaConfig.class)
class StockConcurrencyTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        stockRepository.deleteAll();
    }

    @Test
    void 동시성_문제_재현() throws InterruptedException {
        // given
        Stock stock = stockRepository.save(new Stock(100));

        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    stockService.decrease(stock.getId(), 1);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        // then
        Stock result = stockRepository.findById(stock.getId()).orElseThrow();
        System.out.println("남은 재고 = " + result.getQuantity());

        // 동시성 깨짐 재현 (거의 항상 실패)
        assertThat(result.getQuantity()).isNotEqualTo(0);
    }

    @TestConfiguration
    @EnableJpaRepositories(
            basePackageClasses = StockRepository.class,
            considerNestedRepositories = true
    )
    @EntityScan(basePackageClasses = Stock.class)
    static class TestJpaConfig {

        @Bean
        StockService stockService(StockRepository stockRepository) {
            return new StockService(stockRepository);
        }
    }

    @Entity(name = "Stock")
    @Table(name = "stock")
    static class Stock {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private int quantity;

        @Version
        private Long version;

        protected Stock() {}

        public Stock(int quantity) {
            this.quantity = quantity;
        }

        public Long getId() {
            return id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void decrease(int amount) {
            if (this.quantity < amount) {
                throw new IllegalStateException("재고 부족");
            }
            this.quantity -= amount;
        }
    }

    interface StockRepository extends JpaRepository<Stock, Long> {

        @Lock(LockModeType.PESSIMISTIC_WRITE)
        @Query("select s from Stock s where s.id = :id")
        Stock findByIdForUpdate(@Param("id") Long id);
    }

    @Service
    static class StockService {

        private final StockRepository stockRepository;

        StockService(StockRepository stockRepository) {
            this.stockRepository = stockRepository;
        }

        @Transactional
        public void decrease(Long stockId, int amount) {
            Stock stock = stockRepository.findById(stockId).orElseThrow();
            stock.decrease(amount);
        }

        @Transactional
        public void decreaseWithPessimisticLock(Long stockId, int amount) {
            Stock stock = stockRepository.findByIdForUpdate(stockId);
            stock.decrease(amount);
        }
    }
}
