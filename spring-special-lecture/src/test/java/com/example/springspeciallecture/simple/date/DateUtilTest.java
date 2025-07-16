package com.example.springspeciallecture.simple.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilTest {

    @Test
    void testFormatDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 7, 16, 14, 30, 0);
        String formatted = DateUtil.formatDateTime(dateTime);
        assertEquals("2025-07-16 14:30:00", formatted);
    }
}
