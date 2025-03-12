package com.moiskii.lmsserver.repository;

import com.moiskii.lmsserver.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
//    Book findByTitle(String title);
}
