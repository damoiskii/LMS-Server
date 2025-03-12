package com.moiskii.lmsserver.dto;

import com.moiskii.lmsserver.model.Book;
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

    @NotBlank(message = "Book accession number ID is required!")
    private String accessionNumber;

    public Book build(){
        return new Book(isbn, title, author, status, accessionNumber);
    }
}
