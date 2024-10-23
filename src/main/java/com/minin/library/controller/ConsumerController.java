package com.minin.library.controller;

import com.minin.library.dto.consumer.ConsumerDTO;
import com.minin.library.dto.consumer.MapperConsumerDTO;
import com.minin.library.entity.Consumer;
import com.minin.library.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/consumer")
public class ConsumerController {
    private ConsumerService consumerService;
    private MapperConsumerDTO consumerMapper;


    @PostMapping
    public ResponseEntity<ConsumerDTO> saveConsumer(@RequestBody ConsumerDTO consumerDTO) {
        return ResponseEntity.ok(
                consumerMapper.apply(
                        consumerService.saveConsumer(
                                Consumer.builder()
                                        .firstName(consumerDTO.getFirstName())
                                        .lastName(consumerDTO.getLastName())
                                        .yearBirth(consumerDTO.getYearBirth())
                                        .build()
                        )
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumerDTO> getConsumerById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(
                consumerMapper.apply(
                        consumerService.getConsumerById(id)
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<ConsumerDTO>> getAllConsumersByName(
            @RequestParam(name = "firstName", defaultValue = "") String firstName,
            @RequestParam(name = "lastName", defaultValue = "") String lastName
    ) {
        return ResponseEntity.ok(
                consumerService.findAllConsumerByFirstNameAndLastName(firstName, lastName)
                        .stream().map(consumerMapper::apply).toList()
        );
    }

    @GetMapping("/mostPopular")
    public ResponseEntity<ConsumerDTO> getMostActiveConsumer(
            @RequestParam(name = "from") Long from,
            @RequestParam(name = "to") Long to
    ) {
        return ResponseEntity.ok(
                consumerMapper.apply(
                        consumerService.findMostActiveConsumer(
                                Timestamp.from(Instant.ofEpochMilli(from)),
                                Timestamp.from(Instant.ofEpochMilli(to))
                        )
                )
        );
    }

    @PutMapping
    public ResponseEntity<ConsumerDTO> updateConsumer(@RequestBody ConsumerDTO consumerDTO) {
        return ResponseEntity.ok(
                consumerMapper.apply(
                        consumerService.updateConsumer(
                                Consumer.builder()
                                        .id(consumerDTO.getId())
                                        .firstName(consumerDTO.getFirstName())
                                        .lastName(consumerDTO.getLastName())
                                        .yearBirth(consumerDTO.getYearBirth())
                                        .build()
                        )
                )
        );
    }

}
