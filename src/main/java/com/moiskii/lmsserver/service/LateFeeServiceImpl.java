package com.moiskii.lmsserver.service;

import com.moiskii.lmsserver.exception.LateFeeFoundException;
import com.moiskii.lmsserver.exception.LateFeeNotFoundException;
import com.moiskii.lmsserver.model.LateFee;
import com.moiskii.lmsserver.repository.LateFeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LateFeeServiceImpl implements LateFeeService {
    final LateFeeRepository lateFeeRepository;

    @Override
    public LateFee add(LateFee fee) throws LateFeeFoundException {
        return lateFeeRepository.saveAndFlush(fee);
    }

    @Override
    public LateFee update(Long id, LateFee fee) throws LateFeeNotFoundException {
        LateFee existingLateFee = lateFeeRepository.findById(id).orElseThrow(() -> new LateFeeNotFoundException("LateFee with the id '" + id +"'not found!"));

        existingLateFee.setMember(fee.getMember());
        existingLateFee.setBook(fee.getBook());
        existingLateFee.setCreatedOn(fee.getCreatedOn());
        existingLateFee.setAmount(fee.getAmount());

        return lateFeeRepository.saveAndFlush(existingLateFee);
    }

    @Override
    public LateFee delete(Long id) throws LateFeeNotFoundException {
        LateFee fee = lateFeeRepository.findById(id).orElseThrow(() -> new LateFeeNotFoundException("LateFee with the id '" + id +"'not found!"));

        lateFeeRepository.delete(fee);
        return fee;
    }

    @Override
    public void delete(LateFee fee) throws LateFeeNotFoundException {
        lateFeeRepository.delete(lateFeeRepository.findById(fee.getId()).orElseThrow(() -> new LateFeeNotFoundException("LateFee with the id '" + fee.getId() +"'not found!")));
    }

    @Override
    public void deleteAll() {
        lateFeeRepository.deleteAll();
    }

    @Override
    public LateFee findLateFee(Long id) throws LateFeeNotFoundException {
        return lateFeeRepository.findById(id).orElseThrow(() -> new LateFeeNotFoundException("LateFee with the id '" + id +"'not found!"));
    }

    @Override
    public List<LateFee> findAll() {
        return lateFeeRepository.findAll();
    }

    @Override
    public List<LateFee> findAllByMemberId(Long memberId) {
        return lateFeeRepository.findAllByMember_IdOrderByCreatedOnDesc(memberId);
    }
}
