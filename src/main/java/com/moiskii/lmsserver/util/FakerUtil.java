package com.moiskii.lmsserver.util;

import com.github.javafaker.Faker;
import com.moiskii.lmsserver.model.Book;

import java.util.ArrayList;
import java.util.List;

public class FakerUtil {

    public static List<Book> createFakeBooks(int amount) {
        List<Book> books = new ArrayList<>();
        Faker faker = new Faker();

        for(int i = 0; i < amount; i++){
            Book book = new Book();
            book.setTitle(faker.book().title());
            book.setAuthor(faker.book().author());
            book.setStatus("Available");
            book.setIsbn(faker.code().isbn13());
//            book.setAccessionNumber(faker.code().asin());
            book.setAccessionNumber(faker.regexify("ACC-\\d{4}-[A-Z]\\d{4}"));

            books.add(book);
        }

        return books;
    }
}
