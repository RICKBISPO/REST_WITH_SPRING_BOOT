package com.example.rest_with_spring_boot.mapper.custom;

import com.example.rest_with_spring_boot.data.vo.v2.PersonVOV2;
import com.example.rest_with_spring_boot.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVOV2(Person entity) {
        PersonVOV2 personVOV2 = new PersonVOV2();

        personVOV2.setId(entity.getId());
        personVOV2.setFirstName(entity.getFirstName());
        personVOV2.setLastName(entity.getLastName());
        personVOV2.setAddress(entity.getAddress());
        personVOV2.setGender(entity.getGender());
        personVOV2.setBirthDay(new Date());

        return personVOV2;
    }

    public Person convertVOV2ToEntity(PersonVOV2 personVOV2) {
        Person entity = new Person();

        entity.setId(personVOV2.getId());
        entity.setFirstName(personVOV2.getFirstName());
        entity.setLastName(personVOV2.getLastName());
        entity.setAddress(personVOV2.getAddress());
        entity.setGender(personVOV2.getGender());

        return entity;
    }
}
