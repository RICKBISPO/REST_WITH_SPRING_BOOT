package com.example.rest_with_spring_boot.unittests.mockito.services;

import com.example.rest_with_spring_boot.data.vo.v1.BookVO;
import com.example.rest_with_spring_boot.exceptions.RequiredObjectNullException;
import com.example.rest_with_spring_boot.model.Book;
import com.example.rest_with_spring_boot.repositories.BookRepository;
import com.example.rest_with_spring_boot.services.BookServices;
import com.example.rest_with_spring_boot.unittests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    MockBook input;

    @InjectMocks
    private BookServices service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {

        int id = 1;
        Book entity = input.mockEntity(id);
        entity.setId(id);

        when(repository.findById(id)).thenReturn(java.util.Optional.of(entity));

        var result = service.findById(id);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test1", result.getAuthor());
        assertEquals(Double.valueOf(id), result.getPrice());
        assertEquals("Title Test1", result.getTitle());
    }

    @Test
    void findAll() {

        List<Book> listEntity = input.mockEntityList();

        when(repository.findAll()).thenReturn(listEntity);

        var results = service.findAll();

        assertNotNull(results);
        assertEquals(14, results.size());

        for (int i = 1; i < results.size(); i = i+2) {

            var book = results.get(i);

            assertNotNull(book);
            assertNotNull(book.getLinks());
            assertTrue(book.toString().contains("links: [</api/book/v1/" + i + ">;rel=\"self\"]"));
            assertEquals("Author Test" + i, book.getAuthor());
            assertEquals(Double.valueOf(i), book.getPrice());
            assertEquals("Title Test" + i, book.getTitle());
        }
    }

    @Test
    void create() {

        int id = 1;
        Book entity = input.mockEntity(id);
        entity.setId(id);

        Book persistedEntity = entity;
        persistedEntity.setId(id);

        BookVO vo = input.mockVO(id);
        vo.setKey(id);

        when(repository.save(entity)).thenReturn(persistedEntity);

        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test1", result.getAuthor());
        assertEquals(Double.valueOf(id), result.getPrice());
        assertEquals("Title Test1", result.getTitle());
    }

    @Test
    void createWithNullBooK() {

        Exception exception = assertThrows(RequiredObjectNullException.class, () -> {
            service.create(null);
        });

        String expectedMessage = "It is not allowed to persist a null object.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {

        int id = 1;
        Book entity = input.mockEntity(id);

        Book persistedEntity = entity;
        entity.setId(id);

        BookVO vo = input.mockVO(id);
        vo.setKey(id);

        when(repository.findById(id)).thenReturn(java.util.Optional.of(entity));
        when(repository.save(entity)).thenReturn(persistedEntity);

        var result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test1", result.getAuthor());
        assertEquals(Double.valueOf(id), result.getPrice());
        assertEquals("Title Test1", result.getTitle());
    }

    @Test
    void delete() {

        int id = 1;
        Book entity = input.mockEntity(id);
        entity.setId(id);

        when(repository.findById(id)).thenReturn(java.util.Optional.of(entity));

        service.delete(id);
    }
}