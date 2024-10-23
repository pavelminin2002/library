package com.minin.library.repository;

import com.minin.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findAllByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(String title, String author);

    @Query("select b from Book b inner join Order o on o.book.id = b.id where o.orderTime >= :from and o.returnTime <= :to group by b.id order by count(*) desc limit 1")
    Optional<Book> findTheMostPopularBook(Timestamp from, Timestamp to);
}
