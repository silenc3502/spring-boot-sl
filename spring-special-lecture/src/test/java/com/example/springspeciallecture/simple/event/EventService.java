package com.example.springspeciallecture.simple.event;

import java.time.LocalDateTime;

public class EventService {

    public boolean isEventOngoing(LocalDateTime start, LocalDateTime end, LocalDateTime now) {
        return !now.isBefore(start) && now.isBefore(end);
    }

    public String getTimePeriodMessage(LocalDateTime time) {
        int hour = time.getHour();
        if (hour >= 6 && hour < 12) return "Morning event";
        else if (hour >= 12 && hour < 18) return "Afternoon event";
        else return "Evening event";
    }
}
