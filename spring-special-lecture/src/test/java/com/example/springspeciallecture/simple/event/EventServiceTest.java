package com.example.springspeciallecture.simple.event;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventServiceTest {

    private final EventService eventService = new EventService();

    @Test
    void 이벤트_진행중인_시간_내부이면_참을_반환한다() {
        LocalDateTime 시작시간 = LocalDateTime.of(2025, 7, 16, 9, 0);
        LocalDateTime 종료시간 = LocalDateTime.of(2025, 7, 16, 17, 0);
        LocalDateTime 현재시간 = LocalDateTime.of(2025, 7, 16, 10, 0);
        assertTrue(eventService.isEventOngoing(시작시간, 종료시간, 현재시간));
    }

    @Test
    void 이벤트_시작시간_이전이면_거짓을_반환한다() {
        LocalDateTime 시작시간 = LocalDateTime.of(2025, 7, 16, 9, 0);
        LocalDateTime 종료시간 = LocalDateTime.of(2025, 7, 16, 17, 0);
        LocalDateTime 현재시간 = LocalDateTime.of(2025, 7, 16, 8, 59);
        assertFalse(eventService.isEventOngoing(시작시간, 종료시간, 현재시간));
    }

    @Test
    void 이벤트_종료시간_이후이면_거짓을_반환한다() {
        LocalDateTime 시작시간 = LocalDateTime.of(2025, 7, 16, 9, 0);
        LocalDateTime 종료시간 = LocalDateTime.of(2025, 7, 16, 17, 0);
        LocalDateTime 현재시간 = LocalDateTime.of(2025, 7, 16, 17, 0);
        assertFalse(eventService.isEventOngoing(시작시간, 종료시간, 현재시간));
    }

    @Test
    void 아침_시간대이면_아침_메시지를_반환한다() {
        LocalDateTime 시간 = LocalDateTime.of(2025, 7, 16, 7, 30);
        assertEquals("Morning event", eventService.getTimePeriodMessage(시간));
    }

    @Test
    void 오후_시간대이면_오후_메시지를_반환한다() {
        LocalDateTime 시간 = LocalDateTime.of(2025, 7, 16, 13, 0);
        assertEquals("Afternoon event", eventService.getTimePeriodMessage(시간));
    }

    @Test
    void 저녁_시간대이면_저녁_메시지를_반환한다() {
        LocalDateTime 시간 = LocalDateTime.of(2025, 7, 16, 20, 0);
        assertEquals("Evening event", eventService.getTimePeriodMessage(시간));
    }
}

