package com.moiskii.lmsserver.service;

import com.moiskii.lmsserver.exception.MemberFoundException;
import com.moiskii.lmsserver.exception.MemberNotFoundException;
import com.moiskii.lmsserver.model.Member;
import com.moiskii.lmsserver.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    final MemberRepository memberRepository;

    @Override
    public Member add(Member member) throws MemberFoundException {
        // Check if username exist
        if(memberRepository.findByUsernameEqualsIgnoreCase(member.getUsername()) != null){
            throw new MemberFoundException("Member with username " + member.getUsername() +  " already exist!");
        }

        // Check if email exist
        if(memberRepository.findByEmailEqualsIgnoreCase(member.getEmail()) != null){
            throw new MemberFoundException("Member with email " + member.getEmail() +  " already exist!");
        }

        return memberRepository.saveAndFlush(member);
    }

    @Override
    public Member update(Long id, Member member) throws MemberNotFoundException, MemberFoundException {
        // If the member doesn't exist then throw error
        Member existingMember = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException("Member with id " + id + " not found!"));

        // Check if the username is different but exist
        if(!existingMember.getUsername().equalsIgnoreCase(member.getUsername()) && memberRepository.findByEmailEqualsIgnoreCase(member.getUsername()) != null){
            throw new MemberFoundException("Member with username " + member.getUsername() +  " already exist!");
        }

        // Check if the email is different  exist
        if(!existingMember.getEmail().equalsIgnoreCase(member.getEmail()) && memberRepository.findByEmailEqualsIgnoreCase(member.getEmail()) != null){
            throw new MemberFoundException("Member with email " + member.getEmail() +  " already exist!");
        }

        // Update the existing member object then save to database
        existingMember.setEmail(member.getEmail());
        existingMember.setUsername(member.getUsername());
        existingMember.setPassword(member.getPassword());
        existingMember.setFirstname(member.getFirstname());
        existingMember.setLastname(member.getLastname());
        existingMember.setAddress(member.getAddress());
        existingMember.setDob(member.getDob());
        existingMember.setPhoneNumber(member.getPhoneNumber());
        existingMember.setAllowance(member.getAllowance());
        existingMember.setType(member.getType());
        existingMember.setBookLoans(member.getBookLoans());
        existingMember.setLateFees(member.getLateFees());

        return memberRepository.saveAndFlush(existingMember);
    }

    @Override
    public Member delete(Long id) throws MemberNotFoundException {
        Optional<Member> memberOptional = memberRepository.findById(id);

        // If the member doesn't exist then throw error
        if(memberOptional.isEmpty()){
            throw new MemberNotFoundException("Member with id " + id + " not found!");
        }

        memberRepository.delete(memberOptional.get());
        return memberOptional.get();
    }

    @Override
    public void delete(Member member) throws MemberNotFoundException {
        // If the member doesn't exist then throw error
        if(memberRepository.findById(member.getId()).isEmpty()){
            throw new MemberNotFoundException("Member not found!");
        }

        memberRepository.delete(member);
    }

    @Override
    public void deleteAll() {
        memberRepository.deleteAll();
    }

    @Override
    public Member findMember(Long id) throws MemberNotFoundException {
        return memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException("Member with id " + id + " not found!"));
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public List<Member> findAllByType(String type) {
        return memberRepository.findAllByTypeEqualsIgnoreCaseOrderByIdDesc(type);
    }

    @Override
    public List<Member> findAllByAllowance(String allowance) {
        return memberRepository.findAllByAllowanceEqualsIgnoreCaseOrderByIdDesc(allowance);
    }
}
