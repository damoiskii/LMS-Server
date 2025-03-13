package com.moiskii.lmsserver.dto;

import com.moiskii.lmsserver.model.Book;
import com.moiskii.lmsserver.model.Loan;
import com.moiskii.lmsserver.model.Member;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class LoanResponseData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Book book;
    private Member member;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;

    private String message;

    public void copy(Loan loan) {
        id = loan.getId();
        book = loan.getBook();
        member = loan.getMember();
        borrowDate = loan.getBorrowDate();
        returnDate = loan.getReturnDate();
    }
}
