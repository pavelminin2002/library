package com.minin.library.dto.consumer;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ConsumerDTO implements Serializable {
    private UUID id;
    private String firstName;
    private String lastName;
    private int yearBirth;
    private List<ConsumerOrder> orders;


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class ConsumerOrder implements Serializable {
        private UUID id;
        private Timestamp orderTime;
        private Timestamp returnTime;
        private boolean isReturn;
        private ConsumerBook book;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class ConsumerBook implements Serializable {
        private UUID id;
        private String title;
        private String author;
    }

}
