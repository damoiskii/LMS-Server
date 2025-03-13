package com.moiskii.lmsserver.repository;

import com.moiskii.lmsserver.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    boolean existsByBook_IsbnEqualsIgnoreCase(String isbn);
    List<Loan> findAllByMember_IdOrderByBorrowDateDesc(Long memberId);
}
