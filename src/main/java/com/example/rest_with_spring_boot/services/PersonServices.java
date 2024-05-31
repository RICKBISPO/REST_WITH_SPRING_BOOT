package com.example.rest_with_spring_boot.services;

import com.example.rest_with_spring_boot.controller.PersonController;
import com.example.rest_with_spring_boot.data.vo.v1.PersonVO;
import com.example.rest_with_spring_boot.exceptions.RequiredObjectNullException;
import com.example.rest_with_spring_boot.exceptions.ResourceNotFoundException;
import com.example.rest_with_spring_boot.mapper.DozerMapper;
import com.example.rest_with_spring_boot.model.Person;
import com.example.rest_with_spring_boot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public PersonVO findById(Long id) {

        logger.info("Find one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class)
                .findById(id))
                .withSelfRel());

        return vo;
    }

    public List<PersonVO> findAll() {

        logger.info("Find all people!");

        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);

        persons.forEach(p ->
           p.add(linkTo(methodOn(PersonController.class)
                    .findById(p.getKey()))
                    .withSelfRel())
        );

        return persons;
    }

    public PersonVO update(PersonVO person) {

        if (person == null) {
            throw new RequiredObjectNullException();
        }

        logger.info("Updating one person!");

        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class)
                .findById(vo.getKey()))
                .withSelfRel());

        return vo;
    }

    public PersonVO create(PersonVO person) {

        if (person == null) {
            throw new RequiredObjectNullException();
        }

        logger.info("Creating one person!");

        var entity = DozerMapper.parseObject(person, Person.class);

        PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class)
                .findById(vo.getKey()))
                .withSelfRel());

        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }
}
