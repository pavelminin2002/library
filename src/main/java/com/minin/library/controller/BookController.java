package com.minin.library.controller;

import com.minin.library.dto.book.BookDTO;
import com.minin.library.dto.book.MapperBookDTO;
import com.minin.library.entity.Book;
import com.minin.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final MapperBookDTO bookMapper;

    @PostMapping
    public ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(
                bookMapper.apply(
                        bookService.saveBook(
                                Book.builder()
                                        .title(bookDTO.getTitle())
                                        .author(bookDTO.getAuthor())
                                        .build()
                        )
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBookByTitleAndAuthor(
            @RequestParam(name = "title", defaultValue = "") String title,
            @RequestParam(name = "author", defaultValue = "") String author) {

        return ResponseEntity.ok(

                bookService.findAllBookByTitleAndAuthor(title, author)
                        .stream()
                        .map(bookMapper::apply)
                        .toList()

        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(
                bookMapper.apply(
                        bookService.getBookById(id)
                )
        );
    }

    @GetMapping("/mostPopular")
    public ResponseEntity<BookDTO> getMostPopularBook(
            @RequestParam(name = "from") Long from,
            @RequestParam(name = "to") Long to
    ) {
        return ResponseEntity.ok(
                bookMapper.apply(
                        bookService.findTheMostPopularBook(
                                Timestamp.from(Instant.ofEpochMilli(from)),
                                Timestamp.from(Instant.ofEpochMilli(to)))
                )
        );
    }

    @PutMapping
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(
                bookMapper.apply(
                        bookService.updateBook(
                                Book.builder()
                                        .id(bookDTO.getId())
                                        .title(bookDTO.getTitle())
                                        .author(bookDTO.getAuthor())
                                        .build()
                        )
                )
        );
    }


}
