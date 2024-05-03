package com.example.rest_with_spring_boot.data.vo.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

@JsonPropertyOrder({"id", "first_name", "last_name", "address", "gender", "birth_day"})
@EqualsAndHashCode
@NoArgsConstructor
@Getter @Setter
public class PersonVOV2 extends RepresentationModel<PersonVOV2> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String address;
    private String gender;

    @JsonProperty("birth_day")
    private Date birthDay;
    
}
