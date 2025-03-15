package com.moiskii.lmsserver.service;

import com.moiskii.lmsserver.exception.LateFeeFoundException;
import com.moiskii.lmsserver.exception.LateFeeNotFoundException;
import com.moiskii.lmsserver.model.LateFee;

import java.util.List;

public interface LateFeeService {
    LateFee add(LateFee fee) throws LateFeeFoundException;
    LateFee update(Long id, LateFee fee) throws LateFeeNotFoundException;
    LateFee delete(Long id) throws LateFeeNotFoundException;
    void delete(LateFee fee) throws LateFeeNotFoundException;
    void deleteAll();
    LateFee findLateFee(Long id) throws LateFeeNotFoundException;
    List<LateFee> findAll();
    List<LateFee> findAllByMemberId(Long memberId);
}
