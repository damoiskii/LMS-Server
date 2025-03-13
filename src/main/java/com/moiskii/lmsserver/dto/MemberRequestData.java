package com.moiskii.lmsserver.dto;

import com.moiskii.lmsserver.model.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class MemberRequestData {
    @NotBlank(message = "Member username is required!")
    private String username;

    @Email(message = "Please enter a valid email address!")
    @NotBlank(message = "Member email is required!")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters long!")
    @NotBlank(message = "Member password is required!")
    private String password;

    @NotBlank(message = "Member firstname is required!")
    private String firstname;

    @NotBlank(message = "Member lastname is required!")
    private String lastname;

    @NotBlank(message = "Member phone number is required!")
    private String phoneNumber;

    @NotBlank(message = "Member address is required!")
    private String address;

    @NotBlank(message = "Member type is required!")
    private String type;

    @NotBlank(message = "Member allowance is required!")
    private String allowance;

    @NotBlank(message = "Member dob is required!")
    private LocalDateTime dob;

    public Member build(){
        Member member = new Member();

        member.setUsername(username);
        member.setEmail(email);
        member.setPassword(password);
        member.setFirstname(firstname);
        member.setLastname(lastname);
        member.setPhoneNumber(phoneNumber);
        member.setAddress(address);
        member.setType(type);
        member.setAllowance(allowance);
        member.setDob(dob);

        return member;
    }
}
