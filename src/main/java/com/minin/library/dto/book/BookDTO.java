package com.minin.library.dto.book;

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
public class BookDTO implements Serializable {
    private UUID id;
    private String title;
    private String author;
    private List<BookOrder> orders;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class BookOrder implements Serializable {
        private UUID id;
        private Timestamp orderTime;
        private Timestamp returnTime;
        private boolean isReturn;
        private BookConsumer consumer;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class BookConsumer implements Serializable {
        private UUID id;
        private String firstName;
        private String lastName;
        private int yearBirth;
    }
}
