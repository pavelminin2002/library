package com.minin.library.repository;


import com.minin.library.entity.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, UUID> {
    List<Consumer> findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);

    @Query("select c from Consumer c inner join Order o on o.consumer.id = c.id where o.orderTime >= :from and o.returnTime <= :to group by c.id order by count (*) desc limit 1")
    Optional<Consumer> findMostActiveConsumer (Timestamp from, Timestamp to);
}
