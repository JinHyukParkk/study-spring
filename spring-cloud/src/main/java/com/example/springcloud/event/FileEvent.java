package com.example.springcloud.event;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class FileEvent {

    private String eventId;

    private String type;

    private Map<String, Object> data;

    @Builder
    public FileEvent(String eventId, String type, Map<String, Object> data) {
        this.eventId = eventId;
        this.type = type;
        this.data = data;
    }

    public static FileEvent toCompleteEvent(Map data) {
        return FileEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .type(EventType.COMPLETE.name())
                .data(data)
                .build();
    }

    public static FileEvent toErrorEvent(Map data) {
        return FileEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .type(EventType.COMPLETE.name())
                .data(data)
                .build();
    }
}
