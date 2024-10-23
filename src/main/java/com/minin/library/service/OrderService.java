package com.minin.library.service;

import com.minin.library.entity.Book;
import com.minin.library.entity.Consumer;
import com.minin.library.entity.Order;
import com.minin.library.exception.AlreadyExistsException;
import com.minin.library.exception.NotFoundException;
import com.minin.library.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    private ConsumerService consumerService;
    private BookService bookService;

    public Order getOrder(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("order with id = " + id + " not found"));

    }

    public Order saveOrder(UUID consumerId, UUID bookId) {
        if (orderRepository.findFirstByBookIdAndConsumerId(bookId, consumerId).isPresent())
            throw new AlreadyExistsException("order with book_id = " + bookId + " and consumer_id = " + consumerId + " already exists and not returned");

        Book book = bookService.getBookById(bookId);
        Consumer consumer = consumerService.getConsumerById(consumerId);


        Order order = Order.builder()
                .consumer(consumer)
                .book(book)
                .build();

        return orderRepository.save(order);
    }

    public Order returnedBook(UUID id) {
        Order order = getOrder(id);
        Consumer consumer = order.getConsumer();
        Book book = order.getBook();

        return returnedBook(consumer.getId(), book.getId());
    }

    public Order returnedBook(UUID consumerId, UUID bookId) {
        Order order = orderRepository.findFirstByBookIdAndConsumerId(bookId, consumerId)
                .orElseThrow(() -> new NotFoundException("not returned order with book_id = " + bookId + " and consumer_id = " + consumerId + " not found"));

        order.setReturn(true);
        order.setReturnTime(Timestamp.from(Instant.now()));
        return orderRepository.save(order);
    }

}
