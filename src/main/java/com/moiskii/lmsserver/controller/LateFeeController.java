package com.moiskii.lmsserver.controller;

import com.moiskii.lmsserver.dto.LateFeeRequestData;
import com.moiskii.lmsserver.dto.LateFeeResponseData;
import com.moiskii.lmsserver.exception.LateFeeFoundException;
import com.moiskii.lmsserver.exception.LateFeeNotFoundException;
import com.moiskii.lmsserver.model.LateFee;
import com.moiskii.lmsserver.service.LateFeeServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/late-fees")
@RequiredArgsConstructor
public class LateFeeController {
    final LateFeeServiceImpl lateFeeService;

    @GetMapping("/")
    public ResponseEntity<List<LateFee>> getLateFees() {
        return ResponseEntity.ok().body(lateFeeService.findAll());
    }

    @GetMapping({"/find/{id}", "/find/{id}/"})
    public ResponseEntity<LateFeeResponseData> getLateFee(@PathVariable Long id) {
        LateFeeResponseData response = new LateFeeResponseData();

        try {
            response.copy(lateFeeService.findLateFee(id));
            response.setMessage("Late fee found successfully!");
        } catch (LateFeeNotFoundException e) {
            response.setMessage("Error finding late fee: " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @PostMapping({"/add", "/add/"})
    public ResponseEntity<LateFeeResponseData> addLateFee(@Valid @RequestBody LateFeeRequestData request) {
        LateFeeResponseData response = new LateFeeResponseData();

        try {
            response.copy(lateFeeService.add(request.build()));
            response.setMessage("Late fee added successfully!");
        } catch (LateFeeFoundException e) {
            response.setMessage("Error adding new late fee: " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @PutMapping({"/update/{id}", "/update/{id}/"})
    public ResponseEntity<LateFeeResponseData> updateLateFee(@PathVariable Long id, @Valid @RequestBody LateFeeRequestData request) {
        LateFeeResponseData response = new LateFeeResponseData();

        try {
            response.copy(lateFeeService.update(id, request.build()));
            response.setMessage("Late fee updated successfully!");
        } catch (LateFeeNotFoundException e) {
            response.setMessage("Error updating late fee: " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping({"/delete/{id}", "/delete/{id}/"})
    public ResponseEntity<LateFeeResponseData> deleteLateFee(@PathVariable Long id) {
        LateFeeResponseData response = new LateFeeResponseData();

        try {
            response.copy(lateFeeService.delete(id));
            response.setMessage("Late fee deleted successfully!");
        } catch (LateFeeNotFoundException e) {
            response.setMessage("Error deleting late fee : " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping({"/delete-all", "/delete-all/"})
    public ResponseEntity<String> deleteAllLateFees() {
        lateFeeService.deleteAll();

        return ResponseEntity.ok().body("All late fees deleted successfully!");
    }

    @GetMapping({"/filter-member-id/{id}", "/filter-member-id{id}/"})
    public ResponseEntity<List<LateFee>> filterAllLateFeesByMemberId(@PathVariable Long id) {
        return ResponseEntity.ok().body(lateFeeService.findAllByMemberId(id));
    }
}
