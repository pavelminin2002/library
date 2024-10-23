package com.minin.library.dto.book;

import com.minin.library.entity.Book;
import com.minin.library.entity.Consumer;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class MapperBookDTO implements Function<Book, BookDTO> {
    @Override
    public BookDTO apply(Book book) {
        List<BookDTO.BookOrder> bookOrders = new ArrayList<>();
        if (book.getOrders() != null) {
            bookOrders = book.getOrders().stream()
                    .map(order -> {
                        Consumer consumer = order.getConsumer();
                        return new BookDTO.BookOrder(
                                order.getId(),
                                order.getOrderTime(),
                                order.getReturnTime(),
                                order.isReturn(),
                                new BookDTO.BookConsumer(
                                        consumer.getId(),
                                        consumer.getFirstName(),
                                        consumer.getLastName(),
                                        consumer.getYearBirth()
                                )
                        );
                    }).toList();
        }

        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                bookOrders
        );
    }
}
