package com.minin.library.repository;

import com.minin.library.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {


    @Query("select o from Order o where o.book.id = :bookId and o.consumer.id = :consumerId and o.isReturn = false ")
    Optional<Order> findFirstByBookIdAndConsumerId(UUID bookId, UUID consumerId);

}
