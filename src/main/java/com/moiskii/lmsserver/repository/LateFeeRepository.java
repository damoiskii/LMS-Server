package com.moiskii.lmsserver.repository;

import com.moiskii.lmsserver.model.LateFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LateFeeRepository extends JpaRepository<LateFee, Long> {
    List<LateFee> findAllByMember_IdOrderByCreatedOnDesc(Long memberId);
}
