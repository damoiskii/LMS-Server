package com.moiskii.lmsserver.service;

import com.moiskii.lmsserver.repository.BookRepository;
import com.moiskii.lmsserver.repository.MemberRepository;
import com.moiskii.lmsserver.util.FakerUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Transactional
@RequiredArgsConstructor
public class FakerServiceImpl implements FakerService {
    final BookRepository bookRepository;
    final MemberRepository memberRepository;

    @Async
    @Override
    public CompletableFuture<String> populateFakeBooks(int amount) throws InterruptedException {
        // System.out.println("Executing async method: " + Thread.currentThread().getName());

        bookRepository.saveAll(FakerUtil.createFakeBooks(amount));
        // CompletableFuture run = CompletableFuture.runAsync(() -> {});

        return CompletableFuture.completedFuture("Generated books successfully!");
    }

    @Async
    @Override
    public CompletableFuture<String> populateFakeMembers(int amount) throws InterruptedException {
        memberRepository.saveAll(FakerUtil.createFakeMembers(amount));
        // CompletableFuture run = CompletableFuture.runAsync(() -> {});

        return CompletableFuture.completedFuture("Generated members successfully!");
    }
}
