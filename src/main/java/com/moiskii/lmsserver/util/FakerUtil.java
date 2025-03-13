package com.moiskii.lmsserver.util;

import com.github.javafaker.Faker;
import com.moiskii.lmsserver.model.Book;
import com.moiskii.lmsserver.model.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakerUtil {
    // Create Fake Books
    public static List<Book> createFakeBooks(int amount) {
        List<Book> books = new ArrayList<>();
        Faker faker = new Faker();

        for(int i = 0; i < amount; i++){
            Book book = new Book();
            book.setTitle(faker.book().title());
            book.setAuthor(faker.book().author());
            book.setStatus("Available");
            book.setIsbn(faker.code().isbn13());
            // book.setAccessionNumber(faker.code().asin());
            book.setAccessionNumber(faker.regexify("ACC-\\d{4}-[A-Z]\\d{4}"));

            books.add(book);
        }

        return books;
    }

    // Create Fake Members
    public static List<Member> createFakeMembers(int amount) {
        List<Member> members = new ArrayList<>();
        Faker faker = new Faker();

        for(int i = 0; i < amount; i++){
            Member member = new Member();

            member.setUsername(faker.name().username());
            member.setEmail(faker.internet().emailAddress());
            member.setPassword(faker.internet().password());

            member.setFirstname(faker.name().firstName());
            member.setLastname(faker.name().lastName());
            member.setPhoneNumber(faker.phoneNumber().phoneNumber());
            member.setAddress(faker.address().fullAddress());
            member.setDob(BaseUtil.convertDateToLocalDateTime(faker.date().birthday(20, 100)));
            // member.setDob(faker.date().birthday(20, 100));
            member.setType(getRandomMemberType());
            member.setAllowance(getRandomMemberAllowance(member.getType()));

            members.add(member);
        }

        return members;
    }

    public static String getRandomMemberType(){
        List<String> types = List.of("Ordinary", "Administrator");

        return types.get(new Random().nextInt(types.size()));
    }

    public static String getRandomMemberAllowance(String type){
        if(type.equalsIgnoreCase("ordinary")){
            return "Uniform";
        }

        return "Travel";
    }
}
