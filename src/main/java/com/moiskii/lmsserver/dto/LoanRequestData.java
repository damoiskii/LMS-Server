package com.moiskii.lmsserver.dto;

import com.moiskii.lmsserver.model.Book;
import com.moiskii.lmsserver.model.Loan;
import com.moiskii.lmsserver.model.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class LoanRequestData {
    @NotBlank(message = "Book to loan is required!")
    private Book book;

    @NotBlank(message = "Member for the loan is required!")
    private Member member;

    @NotBlank(message = "Loan borrow date is required!")
    private LocalDateTime borrowDate;

    @NotBlank(message = "Loan return date is required!")
    private LocalDateTime returnDate;

    public Loan build(){
        Loan loan = new Loan();

        loan.setBook(book);
        loan.setMember(member);
        loan.setBorrowDate(borrowDate);
        loan.setReturnDate(returnDate);

        return loan;
    }
}
