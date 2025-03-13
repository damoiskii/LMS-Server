package com.moiskii.lmsserver.service;


import com.moiskii.lmsserver.exception.MemberFoundException;
import com.moiskii.lmsserver.exception.MemberNotFoundException;
import com.moiskii.lmsserver.model.Member;

import java.util.List;

public interface MemberService {
    Member add(Member member) throws MemberFoundException;
    Member update(Long id, Member member) throws MemberNotFoundException, MemberFoundException;
    Member delete(Long id) throws MemberNotFoundException;
    void delete(Member member) throws MemberNotFoundException;
    void deleteAll();
    Member findMember(Long id) throws MemberNotFoundException;
    List<Member> findAll();
    List<Member> findAllByType(String type);
    List<Member> findAllByAllowance(String allowance);
}
