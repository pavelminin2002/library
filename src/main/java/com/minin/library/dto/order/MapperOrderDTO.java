package com.minin.library.dto.order;

import com.minin.library.entity.Book;
import com.minin.library.entity.Consumer;
import com.minin.library.entity.Order;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MapperOrderDTO implements Function<Order, OrderDTO> {

    @Override
    public OrderDTO apply(Order order) {
        Book book = order.getBook();
        Consumer consumer = order.getConsumer();

        return new OrderDTO(
                order.getId(),
                order.getOrderTime(),
                order.getReturnTime(),
                order.isReturn(),
                new OrderDTO.BookInfo(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor()
                ),
                new OrderDTO.ConsumerInfo(
                        consumer.getId(),
                        consumer.getFirstName(),
                        consumer.getLastName(),
                        consumer.getYearBirth()
                )
        );
    }
}
