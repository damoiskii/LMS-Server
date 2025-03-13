package com.moiskii.lmsserver.service;

import com.moiskii.lmsserver.exception.BookFoundException;
import com.moiskii.lmsserver.exception.BookNotFoundException;
import com.moiskii.lmsserver.model.Book;
import com.moiskii.lmsserver.model.LateFee;
import com.moiskii.lmsserver.model.Loan;
import com.moiskii.lmsserver.repository.BookRepository;
import com.moiskii.lmsserver.util.FakerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    final BookRepository bookRepository;

    @Override
    public Book add(Book book) throws BookFoundException {
        // If the book doesn't exist then throw error
        if(bookRepository.findById(book.getIsbn()).isPresent()){
            throw new BookFoundException("Book with isbn '" + book.getIsbn() + "' already exist!");
        }

        return bookRepository.saveAndFlush(book);
    }

    @Override
    public Book update(String isbn, Book book) throws BookNotFoundException {
        // If the book doesn't exist then throw error
        // Book existingBook = bookRepository.findById(isbn).orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " not found"));

//        existingBook.setTitle(book.getTitle());
//        existingBook.setAuthor(book.getAuthor());
//        existingBook.setStatus(book.getStatus());
//        existingBook.setAccessionNumber(book.getAccessionNumber());
//
//        if (book.getLoan() != null) {
//            Loan loan = book.getLoan();
//            loan.setBook(existingBook);
//            existingBook.setLoan(loan);
//        }
//
//        if (book.getLateFee() != null) {
//            LateFee lateFee = book.getLateFee();
//            lateFee.setBook(existingBook);
//            existingBook.setLateFee(lateFee);
//        }

        // If the book doesn't exist then throw error
        if(bookRepository.findById(book.getIsbn()).isPresent()){
            throw new BookNotFoundException("Book with isbn '" + book.getIsbn() + "' already exist!");
        }

        return bookRepository.saveAndFlush(book);
    }

    @Override
    public Book delete(String isbn) throws BookNotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(isbn);

        // If the book doesn't exist then throw error
        if(bookOptional.isEmpty()){
            throw new BookNotFoundException("Book not found!");
        }

        bookRepository.delete(bookOptional.get());
        return bookOptional.get();
    }

    @Override
    public void delete(Book book) throws BookNotFoundException {
        // If the book doesn't exist then throw error
        if(bookRepository.findById(book.getIsbn()).isEmpty()){
            throw new BookNotFoundException("Book not found!");
        }

        bookRepository.delete(book);
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    public Book findBook(String isbn) throws BookNotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(isbn);

        // If the book doesn't exist then throw error
        if(bookOptional.isEmpty()){
            throw new BookNotFoundException("Book not found!");
        }

        return bookOptional.get();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Async
    public CompletableFuture<String> populateFakeBooks(int amount) throws InterruptedException {
        System.out.println("Executing async method: " + Thread.currentThread().getName());
//        Thread.sleep(3000);  // Simulate a delay

        bookRepository.saveAll(FakerUtil.createFakeBooks(amount));

        return CompletableFuture.completedFuture("Task Completed");
    }
}
