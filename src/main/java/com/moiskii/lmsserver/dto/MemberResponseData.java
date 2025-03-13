package com.moiskii.lmsserver.dto;

import com.moiskii.lmsserver.model.Member;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MemberResponseData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;
    //private String password;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String address;
    private String type;
    private String allowance;
    private LocalDateTime dob;

    private String message;

    public void copy(Member member){
        id = member.getId();
        username = member.getUsername();
        email = member.getEmail();
        //password = member.getPassword();
        firstname = member.getFirstname();
        lastname = member.getLastname();
        phoneNumber = member.getPhoneNumber();
        address = member.getAddress();
        type = member.getType();
        allowance = member.getAllowance();
        dob = member.getDob();
    }
}
