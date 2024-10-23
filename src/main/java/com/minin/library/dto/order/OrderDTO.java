package com.minin.library.dto.order;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class OrderDTO implements Serializable {
    private UUID id;
    private Timestamp orderTime;
    private Timestamp returnTime;
    private boolean isReturn;
    private BookInfo book;
    private ConsumerInfo consumer;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class BookInfo implements Serializable {
        private UUID id;
        private String title;
        private String author;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class ConsumerInfo implements Serializable {
        private UUID id;
        private String firstName;
        private String lastName;
        private int yearBirth;
    }


}
