package com.moiskii.lmsserver.controller;

import com.moiskii.lmsserver.dto.MemberRequestData;
import com.moiskii.lmsserver.dto.MemberResponseData;
import com.moiskii.lmsserver.exception.MemberFoundException;
import com.moiskii.lmsserver.exception.MemberNotFoundException;
import com.moiskii.lmsserver.model.Member;
import com.moiskii.lmsserver.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/")
    public ResponseEntity<List<Member>> getMembers() {
        return ResponseEntity.ok().body(memberService.findAll());
    }

    @GetMapping({"/find/{id}", "/find/{id}/"})
    public ResponseEntity<MemberResponseData> getMember(@PathVariable Long id) {
        MemberResponseData response = new MemberResponseData();

        try {
            response.copy(memberService.findMember(id));
            response.setMessage("Member found successfully!");
        } catch (MemberNotFoundException e) {
            response.setMessage("Error finding member: " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @PostMapping({"/add", "/add/"})
    public ResponseEntity<MemberResponseData> addMember(@Valid @RequestBody MemberRequestData request) {
        MemberResponseData response = new MemberResponseData();

        try {
            response.copy(memberService.add(request.build()));
            response.setMessage("Member added successfully!");
        } catch (MemberFoundException e) {
            response.setMessage("Error adding new member: " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @PutMapping({"/update/{id}", "/update/{id}/"})
    public ResponseEntity<MemberResponseData> updateMember(@PathVariable Long id, @Valid @RequestBody MemberRequestData request) {
        MemberResponseData response = new MemberResponseData();

        try {
            response.copy(memberService.update(id, request.build()));
            response.setMessage("Member updated successfully!");
        } catch (MemberNotFoundException | MemberFoundException e) {
            response.setMessage("Error updating member: " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping({"/delete/{id}", "/delete/{id}/"})
    public ResponseEntity<MemberResponseData> deleteMember(@PathVariable Long id) {
        MemberResponseData response = new MemberResponseData();

        try {
            response.copy(memberService.delete(id));
            response.setMessage("Member deleted successfully!");
        } catch (MemberNotFoundException e) {
            response.setMessage("Error deleting member: " + e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping({"/delete-all", "/delete-all/"})
    public ResponseEntity<String> deleteAllMembers() {
        memberService.deleteAll();

        return ResponseEntity.ok().body("All members deleted successfully!");
    }

    @GetMapping({"/filter-type/{type}", "/filter-type/{type}/"})
    public ResponseEntity<List<Member>> filterAllMembersByType(@PathVariable String type) {
        return ResponseEntity.ok().body(memberService.findAllByType(type));
    }

    @GetMapping({"/filter-allowance/{allowance}", "/filter-allowance/{allowance}/"})
    public ResponseEntity<List<Member>> filterAllMembersByAllowance(@PathVariable String allowance) {
        return ResponseEntity.ok().body(memberService.findAllByAllowance(allowance));
    }
}
