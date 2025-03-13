package com.moiskii.lmsserver.controller;

import com.moiskii.lmsserver.dto.LoanRequestData;
import com.moiskii.lmsserver.dto.LoanResponseData;
import com.moiskii.lmsserver.exception.LoanFoundException;
import com.moiskii.lmsserver.exception.LoanNotFoundException;
import com.moiskii.lmsserver.model.Loan;
import com.moiskii.lmsserver.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {
    final LoanService loanService;

    @GetMapping("/")
    public ResponseEntity<List<Loan>> getLoans() {
        return ResponseEntity.ok().body(loanService.findAll());
    }

    @GetMapping({"/find/{id}", "/find/{id}/"})
    public ResponseEntity<LoanResponseData> getLoan(@PathVariable Long id) {
        LoanResponseData response = new LoanResponseData();

        try {
            response.copy(loanService.findLoan(id));
            response.setMessage("Loan found successfully!");
        } catch (LoanNotFoundException e) {
            response.setMessage("Error finding loan: " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @PostMapping({"/add", "/add/"})
    public ResponseEntity<LoanResponseData> addLoan(@Valid @RequestBody LoanRequestData request) {
        LoanResponseData response = new LoanResponseData();

        try {
            response.copy(loanService.add(request.build()));
            response.setMessage("Loan added successfully!");
        } catch (LoanFoundException e) {
            response.setMessage("Error adding new loan: " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @PutMapping({"/update/{id}", "/update/{id}/"})
    public ResponseEntity<LoanResponseData> updateLoan(@PathVariable Long id, @Valid @RequestBody LoanRequestData request) {
        LoanResponseData response = new LoanResponseData();

        try {
            response.copy(loanService.update(id, request.build()));
            response.setMessage("Loan updated successfully!");
        } catch (LoanNotFoundException e) {
            response.setMessage("Error updating loan: " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping({"/delete/{id}", "/delete/{id}/"})
    public ResponseEntity<LoanResponseData> deleteLoan(@PathVariable Long id) {
        LoanResponseData response = new LoanResponseData();

        try {
            response.copy(loanService.delete(id));
            response.setMessage("Loan deleted successfully!");
        } catch (LoanNotFoundException e) {
            response.setMessage("Error deleting loan: " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping({"/delete-all", "/delete-all/"})
    public ResponseEntity<String> deleteAllLoans() {
        loanService.deleteAll();

        return ResponseEntity.ok().body("All loans deleted successfully!");
    }

    @GetMapping({"/filter-member-id/{id}", "/filter-member-id{id}/"})
    public ResponseEntity<List<Loan>> filterAllLoansByMemberId(@PathVariable Long id) {
        return ResponseEntity.ok().body(loanService.findAllByMemberId(id));
    }
}
