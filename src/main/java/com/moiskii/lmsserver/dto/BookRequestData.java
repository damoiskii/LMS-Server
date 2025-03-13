package com.moiskii.lmsserver.dto;

import com.moiskii.lmsserver.model.Book;
import com.moiskii.lmsserver.model.LateFee;
import com.moiskii.lmsserver.model.Loan;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class BookRequestData {
    @NotBlank(message = "Book isbn number is required!")
    private String isbn;

    @NotBlank(message = "Book title is required!")
    private String title;

    @NotBlank(message = "Book author name is required!")
    private String author;

    @NotBlank(message = "Book status is required!")
    private String status;

    @NotBlank(message = "Book accession number is required!")
    private String accessionNumber;

    private Loan loan;
    private LateFee lateFee;

    public Book build(){
         return new Book(isbn, title, author, status, accessionNumber, loan, lateFee);
    }
}
