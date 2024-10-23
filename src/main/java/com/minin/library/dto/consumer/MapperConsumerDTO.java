package com.minin.library.dto.consumer;

import com.minin.library.entity.Book;
import com.minin.library.entity.Consumer;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MapperConsumerDTO implements Function<Consumer, ConsumerDTO> {
    @Override
    public ConsumerDTO apply(Consumer consumer) {
        List<ConsumerDTO.ConsumerOrder> consumerOrders = new ArrayList<>();
        if (consumer.getOrders() != null) {
            consumerOrders = consumer.getOrders().stream()
                    .map(order -> {
                        Book book = order.getBook();
                        return new ConsumerDTO.ConsumerOrder(
                                order.getId(),
                                order.getOrderTime(),
                                order.getReturnTime(),
                                order.isReturn(),
                                new ConsumerDTO.ConsumerBook(
                                        book.getId(),
                                        book.getTitle(),
                                        book.getAuthor()
                                )
                        );
                    }).toList();
        }

        return new ConsumerDTO(
                consumer.getId(),
                consumer.getFirstName(),
                consumer.getLastName(),
                consumer.getYearBirth(),
                consumerOrders
        );
    }
}
