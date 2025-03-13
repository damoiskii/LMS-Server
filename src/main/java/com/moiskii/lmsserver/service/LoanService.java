package com.moiskii.lmsserver.service;

import com.moiskii.lmsserver.exception.LoanFoundException;
import com.moiskii.lmsserver.exception.LoanNotFoundException;
import com.moiskii.lmsserver.model.Loan;

import java.util.List;

public interface LoanService {
    Loan add(Loan loan) throws LoanFoundException;
    Loan update(Long id, Loan loan) throws LoanNotFoundException;
    Loan delete(Long id) throws LoanNotFoundException;
    void delete(Loan loan) throws LoanNotFoundException;
    void deleteAll();
    Loan findLoan(Long id) throws LoanNotFoundException;
    List<Loan> findAll();
    List<Loan> findAllByMemberId(Long memberId);
}
