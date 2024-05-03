package com.example.rest_with_spring_boot.unittests.mockito.services;

import com.example.rest_with_spring_boot.data.vo.v1.PersonVO;
import com.example.rest_with_spring_boot.data.vo.v2.PersonVOV2;
import com.example.rest_with_spring_boot.exceptions.RequiredObjectNullException;
import com.example.rest_with_spring_boot.mapper.custom.PersonMapper;
import com.example.rest_with_spring_boot.model.Person;
import com.example.rest_with_spring_boot.repositories.PersonRepository;
import com.example.rest_with_spring_boot.services.PersonServices;
import com.example.rest_with_spring_boot.unittests.mapper.mocks.MockPerson;
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
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;

    @Mock
    PersonMapper personMapper;

    @BeforeEach
    void setUp() throws Exception {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {

        long id = 1L;
        Person entity = input.mockEntity(1);
        entity.setId(id);

        when(repository.findById(id)).thenReturn(java.util.Optional.of(entity));

        var result = service.findById(id);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void findAll() {

        List<Person> listEntity = input.mockEntityList();

        when(repository.findAll()).thenReturn(listEntity);

        var results = service.findAll();

        assertNotNull(results);
        assertEquals(14, results.size());

        for (int i = 1; i < results.size(); i = i+2) {

            var person = results.get(i);

            assertNotNull(person);
            assertNotNull(person.getKey());
            assertNotNull(person.getLinks());
            assertTrue(person.toString().contains("links: [</api/person/v1/" + i + ">;rel=\"self\"]"));
            assertEquals("Addres Test" + i, person.getAddress());
            assertEquals("First Name Test" + i, person.getFirstName());
            assertEquals("Last Name Test" + i, person.getLastName());
            assertEquals("Female", person.getGender());
        }
    }

    @Test
    void update() {

        long id = 1L;
        Person entity = input.mockEntity(1);
        entity.setId(id);

        Person persistedEntity = entity;
        entity.setId(id);

        PersonVO vo = input.mockVO(1);
        vo.setKey(id);

        when(repository.findById(id)).thenReturn(java.util.Optional.of(entity));
        when(repository.save(entity)).thenReturn(persistedEntity);

        var result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void updateWithNullPerson() {

        Exception exception = assertThrows(RequiredObjectNullException.class, () -> {
            service.update(null);
        });

        String expectedMessage = "It is not allowed to persist a null object.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void create() {

        long id = 1L;
        Person entity = input.mockEntity(1);

        Person persistedEntity = entity;
        entity.setId(id);

        PersonVO vo = input.mockVO(1);
        vo.setKey(id);

        when(repository.save(entity)).thenReturn(persistedEntity);

        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void createWithNullPerson() {

        Exception exception = assertThrows(RequiredObjectNullException.class, () -> {
            service.create(null);
        });

        String expectedMessage = "It is not allowed to persist a null object.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createV2() {

        long id = 1L;
        Person entity = input.mockEntity(1);

        Person persistedEntity = entity;
        entity.setId(id);

        PersonVOV2 vov2 = input.mockVOV2(1);
        vov2.setKey(id);

        when(personMapper.convertVOV2ToEntity(vov2)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(persistedEntity);
        when(personMapper.convertEntityToVOV2(persistedEntity)).thenReturn(vov2);

        PersonVOV2 result = service.createV2(vov2);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void createV2WithNullPerson() {

        Exception exception = assertThrows(RequiredObjectNullException.class, () -> {
            service.createV2(null);
        });

        String expectedMessage = "It is not allowed to persist a null object.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {

        long id = 1L;
        Person entity = input.mockEntity(1);
        entity.setId(id);

        when(repository.findById(id)).thenReturn(java.util.Optional.of(entity));

        service.delete(id);
    }
}