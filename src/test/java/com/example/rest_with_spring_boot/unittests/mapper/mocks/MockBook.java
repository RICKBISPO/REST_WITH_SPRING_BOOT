package com.example.rest_with_spring_boot.unittests.mapper.mocks;

import com.example.rest_with_spring_boot.data.vo.v1.BookVO;
import com.example.rest_with_spring_boot.model.Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

    private final LocalDate localDate;
    private Date date;

    public MockBook() {
        this.localDate = LocalDate.parse("2024-05-04");
        this.date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockVO() {
        return mockVO(0);
    }

    public List<Book> mockEntityList() {

        List<Book> books = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));

        }
        return books;
    }

    public List<BookVO> mockVOList() {

        List<BookVO> books = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));

        }

        return books;
    }

    public Book mockEntity(Integer number) {

        Book book = new Book();

        book.setId(number);
        book.setAuthor("Author Test" + number);
        book.setLaunchDate(date);
        book.setPrice(Double.valueOf(number));
        book.setTitle("Title Test" + number);

        return book;
    }

    public BookVO mockVO(Integer number) {

        BookVO book = new BookVO();

        book.setKey(number);
        book.setAuthor("Author Test" + number);
        book.setLaunchDate(date);
        book.setPrice(Double.valueOf(number));
        book.setTitle("Title Test" + number);

        return book;
    }
}
