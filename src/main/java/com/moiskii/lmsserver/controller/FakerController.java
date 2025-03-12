package com.moiskii.lmsserver.controller;

import com.moiskii.lmsserver.service.FakerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/faker")
@RequiredArgsConstructor
public class FakerController {
    final FakerServiceImpl fakerService;

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok().body("You are now at the faker endpoint.");
    }

    @GetMapping({"/generate-books/{amount}", "/generate-books/{amount}/"})
    public CompletableFuture<String> generateBooks(@PathVariable int amount) throws InterruptedException {
        return fakerService.populateFakeBooks(amount);
    }
}
