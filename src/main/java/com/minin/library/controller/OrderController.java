package com.minin.library.controller;

import com.minin.library.dto.order.MapperOrderDTO;
import com.minin.library.dto.order.OrderDTO;
import com.minin.library.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    private MapperOrderDTO orderMapper;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(
                orderMapper.apply(
                        orderService.getOrder(id)
                )
        );
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(
            @RequestParam(name = "consumerId") UUID consumerId,
            @RequestParam(name = "bookId") UUID bookId
    ) {
        return ResponseEntity.ok(
                orderMapper.apply(
                        orderService.saveOrder(consumerId, bookId)
                )
        );
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<OrderDTO> returnOrder(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(
                orderMapper.apply(
                        orderService.returnedBook(id)
                )
        );
    }
}
