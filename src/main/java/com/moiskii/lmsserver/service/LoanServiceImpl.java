package com.moiskii.lmsserver.service;

import com.moiskii.lmsserver.exception.LoanFoundException;
import com.moiskii.lmsserver.exception.LoanNotFoundException;
import com.moiskii.lmsserver.model.Loan;
import com.moiskii.lmsserver.repository.LoanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    final LoanRepository loanRepository;

    @Override
    public Loan add(Loan loan) throws LoanFoundException {
        if(loan.getBook() != null && loanRepository.existsByBook_IsbnEqualsIgnoreCase(loan.getBook().getIsbn())){
            throw new LoanFoundException("Loan with book isbn '" + loan.getBook().getIsbn() + "' already exists!");
        }

        return loanRepository.saveAndFlush(loan);
    }

    @Override
    public Loan update(Long id, Loan loan) throws LoanNotFoundException {
        Loan existingLoan = loanRepository.findById(id).orElseThrow(() -> new LoanNotFoundException("Loan with the id '" + id + "' was not found!"));

        existingLoan.setBook(loan.getBook());
        existingLoan.setMember(loan.getMember());
        existingLoan.setBorrowDate(loan.getBorrowDate());
        existingLoan.setReturnDate(loan.getReturnDate());

        return loanRepository.saveAndFlush(existingLoan);
    }

    @Override
    public Loan delete(Long id) throws LoanNotFoundException {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> new LoanNotFoundException("Loan with the id '" + id + "' was not found!"));

        loanRepository.delete(loan);
        return loan;
    }

    @Override
    public void delete(Loan loan) throws LoanNotFoundException {
        loanRepository.delete(loanRepository.findById(loan.getId()).orElseThrow(() -> new LoanNotFoundException("Loan with the id '" + loan.getId() + "' was not found!")));
    }

    @Override
    public void deleteAll() {
        loanRepository.deleteAll();
    }

    @Override
    public Loan findLoan(Long id) throws LoanNotFoundException {
        return loanRepository.findById(id).orElseThrow(() -> new LoanNotFoundException("Loan with the id '" + id + "' was not found!"));
    }

    @Override
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    @Override
    public List<Loan> findAllByMemberId(Long memberId) {
        return loanRepository.findAllByMember_IdOrderByBorrowDateDesc(memberId);
    }
}
