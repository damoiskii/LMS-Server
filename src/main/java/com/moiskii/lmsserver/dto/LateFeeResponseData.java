package com.moiskii.lmsserver.dto;

import com.moiskii.lmsserver.model.Book;
import com.moiskii.lmsserver.model.LateFee;
import com.moiskii.lmsserver.model.Member;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class LateFeeResponseData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Book book;
    private Member member;
    private LocalDateTime createdOn;
    private Double amount;

    private String message;

    public void copy(LateFee fee) {
        id = fee.getId();
        book = fee.getBook();
        member = fee.getMember();
        createdOn = fee.getCreatedOn();
        amount = fee.getAmount();
    }
}
