package com.moiskii.lmsserver.service;

import com.moiskii.lmsserver.model.Book;
import com.moiskii.lmsserver.model.Member;
import com.moiskii.lmsserver.repository.BookRepository;
import com.moiskii.lmsserver.repository.LoanRepository;
import com.moiskii.lmsserver.repository.MemberRepository;
import com.moiskii.lmsserver.util.FakerUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
@RequiredArgsConstructor
public class FakerServiceImpl implements FakerService {
    final BookRepository bookRepository;
    final MemberRepository memberRepository;
    final LoanRepository loanRepository;

    @Async
    @Override
    public CompletableFuture<String> populateFakeBooks(int amount) throws InterruptedException {
        // System.out.println("Executing async method: " + Thread.currentThread().getName());

        bookRepository.saveAllAndFlush(FakerUtil.createFakeBooks(amount));
        // CompletableFuture run = CompletableFuture.runAsync(() -> {});

        return CompletableFuture.completedFuture("Generated books successfully!");
    }

    @Async
    @Override
    public CompletableFuture<String> populateFakeMembers(int amount) throws InterruptedException {
        memberRepository.saveAllAndFlush(FakerUtil.createFakeMembers(amount));
        // CompletableFuture run = CompletableFuture.runAsync(() -> {});

        return CompletableFuture.completedFuture("Generated members successfully!");
    }

    @Async
    @Override
    public CompletableFuture<String> populateFakeLoans(int amount) throws InterruptedException {
        List<Book> books = bookRepository.findAll();
        List<Member> members = memberRepository.findAll();

        // Generate new books if non exist
        if(books.isEmpty()){
            books = bookRepository.saveAllAndFlush(FakerUtil.createFakeBooks(amount));
        }

        // Generate new members if non exist
        if(members.isEmpty()){
            members = memberRepository.saveAllAndFlush(FakerUtil.createFakeMembers(amount));
        }

        loanRepository.saveAllAndFlush(FakerUtil.createFakeLoans(amount, books, members));

        return CompletableFuture.completedFuture("Generated loans successfully!");
    }
}
