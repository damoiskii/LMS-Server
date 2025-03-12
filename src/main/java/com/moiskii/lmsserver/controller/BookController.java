package com.moiskii.lmsserver.controller;

import com.moiskii.lmsserver.dto.BookRequestData;
import com.moiskii.lmsserver.dto.BookResponseData;
import com.moiskii.lmsserver.exception.BookFoundException;
import com.moiskii.lmsserver.exception.BookNotFoundException;
import com.moiskii.lmsserver.model.Book;
import com.moiskii.lmsserver.model.Loan;
import com.moiskii.lmsserver.service.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    final BookServiceImpl bookService;

    @GetMapping("/")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok().body(bookService.findAll());
    }

    @GetMapping({"/{isbn}", "/{isbn}/"})
    public ResponseEntity<BookResponseData> getBook(@PathVariable String isbn) {
        BookResponseData response = new BookResponseData();

        try {
            response.copy(bookService.findBook(isbn));
            response.setMessage("Book found successfully!");
        } catch (BookNotFoundException e) {
            response.setMessage("Error finding book: " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @GetMapping({"/generate/{amount}", "/generate/{amount}/"})
    public CompletableFuture<String> generate(@PathVariable int amount) throws InterruptedException {
        return bookService.populateFakeBooks(amount);
    }

    @PostMapping({"/add", "/add/"})
    public ResponseEntity<BookResponseData> addBook(@RequestBody BookRequestData request) {
        BookResponseData response = new BookResponseData();

        try {
            response.copy(bookService.add(request.build()));
            response.setMessage("Book added successfully!");
        } catch (BookFoundException e) {
            response.setMessage("Error adding new book: " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @PutMapping({"/update/{isbn}", "/update/{isbn}/"})
    public ResponseEntity<BookResponseData> updateBook(@PathVariable String isbn, @RequestBody BookRequestData request) {
        BookResponseData response = new BookResponseData();

        try {
            response.copy(bookService.update(isbn, request.build()));
            response.setMessage("Book updated successfully!");
        } catch (BookNotFoundException e) {
            response.setMessage("Error updating new book: " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping({"/delete/{isbn}", "/delete/{isbn}/"})
    public ResponseEntity<BookResponseData> deleteBook(@PathVariable String isbn) {
        BookResponseData response = new BookResponseData();

        try {
            response.copy(bookService.delete(isbn));
            response.setMessage("Book deleted successfully!");
        } catch (BookNotFoundException e) {
            response.setMessage("Error deleting new book: " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping({"/delete-all", "/delete-all/"})
    public ResponseEntity<String> deleteAllBooks() {
        bookService.deleteAll();

        return ResponseEntity.ok().body("All books deleted successfully!");
    }
}
