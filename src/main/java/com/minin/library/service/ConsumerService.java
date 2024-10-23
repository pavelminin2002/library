package com.minin.library.service;

import com.minin.library.entity.Consumer;
import com.minin.library.exception.AlreadyExistsException;
import com.minin.library.exception.NotFoundException;
import com.minin.library.repository.ConsumerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConsumerService {
    private ConsumerRepository consumerRepository;

    public Consumer getConsumerById(UUID id) {
        return consumerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("consumer with id " + id + " not found"));
    }

    public List<Consumer> findAllConsumerByFirstNameAndLastName(String firstName, String lastName) {
        List<Consumer> result = consumerRepository.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
        if (result.isEmpty()) {
            throw new NotFoundException("consumer with first name " + firstName + " and last name " + lastName + " not found");
        }

        return result;
    }

    public Consumer saveConsumer(Consumer consumer) {
        return consumerRepository.save(consumer);
    }

    public Consumer updateConsumer(Consumer consumer) {
        if (!consumerRepository.existsById(consumer.getId()))
            throw new NotFoundException("consumer with id " + consumer.getId() + " not found");

        return consumerRepository.save(consumer);
    }

    public Consumer findMostActiveConsumer(Timestamp from, Timestamp to) {
        return consumerRepository.findMostActiveConsumer(from, to)
                .orElseThrow(() -> new NotFoundException("most active consumer not found"));
    }
}
