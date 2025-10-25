package com.example.springspeciallecture.aggregate.repository;

import com.example.springspeciallecture.aggregate.entity.Aggregate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AggregateRepository extends JpaRepository<Aggregate, Long> {
}
