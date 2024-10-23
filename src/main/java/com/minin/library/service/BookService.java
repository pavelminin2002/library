package com.minin.library.service;

import com.minin.library.entity.Book;
import com.minin.library.exception.AlreadyExistsException;
import com.minin.library.exception.NotFoundException;
import com.minin.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public Book getBookById(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("book with id " + id + " not found"));
    }

    public List<Book> findAllBookByTitleAndAuthor(String title, String author) {
        List<Book> result = bookRepository.findAllByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title, author);
        if (result.isEmpty())
            throw new NotFoundException("books with title " + title + " and author " + author + " not found");
        return result;
    }

    public Book saveBook(Book book) {
        //if (bookRepository.existsById(book.getId()))
        //    throw new AlreadyExistsException("book with id " + book.getId() + " already exists");

        return bookRepository.save(book);
    }

    public Book findTheMostPopularBook(Timestamp from, Timestamp to) {
        System.out.println(from);
        System.out.println(to);

        return bookRepository.findTheMostPopularBook(from, to)
                .orElseThrow(() -> new NotFoundException("Not found book"));
    }

    public Book updateBook(Book book) {
        if (!bookRepository.existsById(book.getId()))
            throw new NotFoundException("book with id " + book.getId() + " not found");

        return bookRepository.save(book);
    }

}
