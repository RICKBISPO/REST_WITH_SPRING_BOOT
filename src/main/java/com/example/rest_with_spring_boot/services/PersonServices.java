package com.example.rest_with_spring_boot.services;

import com.example.rest_with_spring_boot.data.vo.v1.PersonVO;
import com.example.rest_with_spring_boot.data.vo.v2.PersonVOV2;
import com.example.rest_with_spring_boot.exceptions.ResourceNotFoundException;
import com.example.rest_with_spring_boot.mapper.DozerMapper;
import com.example.rest_with_spring_boot.mapper.custom.PersonMapper;
import com.example.rest_with_spring_boot.model.Person;
import com.example.rest_with_spring_boot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper personMapper;

    public PersonVO findById(Long id) {

        logger.info("Find one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {

        logger.info("Find all people!");
        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO update(PersonVO person) {

        logger.info("Updating one person!");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public PersonVO create(PersonVO person) {

        logger.info("Creating one person!");

        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = repository.save(entity);

        return DozerMapper.parseObject(vo, PersonVO.class);
    }

    public PersonVOV2 createV2(PersonVOV2 person) {

        logger.info("Creating one person! (V2)");

        var entity = personMapper.convertVOV2ToEntity(person);

        return personMapper.convertEntityToVOV2(repository.save(entity));
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }
}
