package com.example.demo.notification.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

public class NotificationEntityDto {

    @Getter
    @Builder
    public static class Subscribe {
        private String endpoint;
        private Map<String, String> keys;

        public NotificationEntity toEntity() {
            return NotificationEntity.builder()
                    .endpoint(this.endpoint)
                    .p256dh(this.keys.get("pd256dh"))
                    .auth(this.keys.get("auth"))
                    .build();
        }
    }

    @Getter
    @Builder
    public static class Send {
        private Long idx;
        private String title;
        private String message;
    }

    @Getter
    @Builder
    public static class Payload {
        private String title;
        private String message;

        public static Payload from(Send dto) {
            return Payload.builder()
                    .title(dto.getTitle())
                    .message(dto.getMessage())
                    .build();
        }

        @Override
        public String toString() {
            return "{\"title\":\""+this.title+"\", \"message\":\""+this.message+"\"}";
        }
    }
}
