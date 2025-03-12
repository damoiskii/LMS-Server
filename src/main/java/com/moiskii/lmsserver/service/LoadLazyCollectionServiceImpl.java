package com.moiskii.lmsserver.service;


import com.moiskii.lmsserver.model.Member;
import com.moiskii.lmsserver.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LoadLazyCollectionServiceImpl implements LoadLazyCollectionService{
    final BookRepository bookRepository;



    @Override
    public Member start(Member member) {
//        Optional<User> userOptional = userRepository.findById(u.getId());
//
//        if(userOptional.isPresent()) {
//            Hibernate.initialize(userOptional.get());
//            return userOptional.get();
//        }
//
//        User user = userRepository.findByUsernameEqualsIgnoringCase(u.getUsername());
//        Hibernate.initialize(user);
//
//        return user;

        return null;
    }
}
