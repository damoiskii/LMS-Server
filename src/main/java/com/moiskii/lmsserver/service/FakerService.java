package com.moiskii.lmsserver.service;

import java.util.concurrent.CompletableFuture;

public interface FakerService {
    CompletableFuture<String> populateFakeBooks(int amount) throws InterruptedException;
    CompletableFuture<String> populateFakeMembers(int amount) throws InterruptedException;
}
