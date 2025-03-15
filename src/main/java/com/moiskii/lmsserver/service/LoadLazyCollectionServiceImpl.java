package com.moiskii.lmsserver.service;


import com.moiskii.lmsserver.model.Member;
import com.moiskii.lmsserver.repository.BookRepository;
import com.moiskii.lmsserver.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoadLazyCollectionServiceImpl implements LoadLazyCollectionService{
    final BookRepository bookRepository;
    final MemberRepository memberRepository;

    @Override
    public Member start(Member m) {
        Optional<Member> memberOptional = memberRepository.findById(m.getId());

        if(memberOptional.isPresent()) {
            Hibernate.initialize(memberOptional.get());
            return memberOptional.get();
        }

        Member member = memberRepository.findByUsernameEqualsIgnoreCase(m.getUsername());
        Hibernate.initialize(member);

        return member;
    }
}
