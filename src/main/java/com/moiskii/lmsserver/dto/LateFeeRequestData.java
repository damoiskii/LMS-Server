package com.moiskii.lmsserver.dto;

import com.moiskii.lmsserver.model.Book;
import com.moiskii.lmsserver.model.LateFee;
import com.moiskii.lmsserver.model.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class LateFeeRequestData {
    @NotBlank(message = "Book to late fee is required!")
    private Book book;

    @NotBlank(message = "Member for the late fee is required!")
    private Member member;

    @NotBlank(message = "Late fee created on date is required!")
    private LocalDateTime createdOn;

    @NotBlank(message = "Late fee amount is required!")
    private Double amount;

    public LateFee build(){
        LateFee fee = new LateFee();

        fee.setBook(book);
        fee.setMember(member);
        fee.setCreatedOn(createdOn);
        fee.setAmount(amount);

        return fee;
    }
}
