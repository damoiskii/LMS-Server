package com.moiskii.lmsserver.util;

import com.github.javafaker.Faker;
import com.moiskii.lmsserver.model.Book;
import com.moiskii.lmsserver.model.Loan;
import com.moiskii.lmsserver.model.Member;

import java.util.*;
import java.util.concurrent.TimeUnit;

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

    // Generate Member Helper: Get Random Type
    private static String getRandomMemberType(){
        List<String> types = List.of("Ordinary", "Administrator");

        return types.get(new Random().nextInt(types.size()));
    }

    // Generate Member Helper: Get Random Allowance
    private static String getRandomMemberAllowance(String type){
        if(type.equalsIgnoreCase("ordinary")){
            return "Uniform";
        }

        return "Travel";
    }

    // Create Fake Loans
    public static List<Loan> createFakeLoans(int amount, List<Book> books, List<Member> members) {
        Set<String> isbnTracker = new HashSet<>();

        List<Loan> loans = new ArrayList<>();
        Faker faker = new Faker();

        for(int i = 0; i < amount; i++){
            if(i > books.size()) break;

            Loan loan = new Loan();

            // Generate a random future date within the next 30 days
            loan.setBorrowDate(BaseUtil.convertDateToLocalDateTime(faker.date().future(30, TimeUnit.DAYS)));
            loan.setReturnDate(BaseUtil.addDaysToLocalDateTime(14, loan.getBorrowDate()));

            // Get a random member
            loan.setMember(getRandomMember(members));

            // Get a random book
            loan.setBook(getRandomBook(books, isbnTracker));

            loans.add(loan);
        }

        return loans;
    }

    // Generate Loan Helper: Get Random Member
    private static Member getRandomMember(List<Member> members){
        return members.get(new Random().nextInt(members.size()));
    }

    // Generate Loan Helper: Get Random Book
    private static Book getRandomBook(List<Book> books, Set<String> isbnTracker){
        Book book;

        while(true){
            book = books.get(new Random().nextInt(books.size()));

            // Break if isbn tracker doesn't contain the currently picked isbn
            if(!isbnTracker.contains(book.getIsbn())) {
                isbnTracker.add(book.getIsbn());
                break;
            };
        }

        return book;
    }
}
