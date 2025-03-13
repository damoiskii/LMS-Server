package com.moiskii.lmsserver.repository;

import com.moiskii.lmsserver.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsernameEqualsIgnoreCase(String username);
    Member findByEmailEqualsIgnoreCase(String email);

    // List<Member> findAllByOrderByIdDesc();
    List<Member> findAllByTypeEqualsIgnoreCaseOrderByIdDesc(String type);
    List<Member> findAllByAllowanceEqualsIgnoreCaseOrderByIdDesc(String allowance);
}
