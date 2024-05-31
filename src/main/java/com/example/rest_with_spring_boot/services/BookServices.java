package com.example.rest_with_spring_boot.services;

import com.example.rest_with_spring_boot.controller.BookController;
import com.example.rest_with_spring_boot.data.vo.v1.BookVO;
import com.example.rest_with_spring_boot.exceptions.RequiredObjectNullException;
import com.example.rest_with_spring_boot.exceptions.ResourceNotFoundException;
import com.example.rest_with_spring_boot.mapper.DozerMapper;
import com.example.rest_with_spring_boot.model.Book;
import com.example.rest_with_spring_boot.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

@Service

public class BookServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    BookRepository repository;

    public BookVO findById(int id) {

        logger.info("Find one book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class)
                .findById(id))
                .withSelfRel());

        return vo;
    }

    public List<BookVO> findAll() {

        logger.info("Find all books!");

        var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);

        books.forEach(b ->
            b.add(linkTo(methodOn(BookController.class)
                    .findById(b.getKey()))
                    .withSelfRel())
        );

        return books;
    }

    public BookVO create(BookVO book) {

        if (book == null) {
            throw new RequiredObjectNullException();
        }

        logger.info("Create a book!");

        var entity = DozerMapper.parseObject(book, Book.class);

        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class)
                .findById(vo.getKey()))
                .withSelfRel());

        return vo;
    }

    public BookVO update(BookVO book) {

        if (book == null) {
            throw new RequiredObjectNullException();
        }

        logger.info("Update a book!");

        var entity = repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));


        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class)
                .findById(vo.getKey()))
                .withSelfRel());

        return vo;
    }

    public void delete(int id) {

        logger.info("Delete a book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }
}
