package com.moiskii.lmsserver.dto;

import com.moiskii.lmsserver.model.Book;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class BookResponseData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String isbn;
    private String title;
    private String author;
    private String status;
    private String accessionNumber;
    private String message;

    public void copy(Book book){
        isbn = book.getIsbn();
        title = book.getTitle();
        author = book.getAuthor();
        status = book.getStatus();
        accessionNumber = book.getAccessionNumber();
    }
}
