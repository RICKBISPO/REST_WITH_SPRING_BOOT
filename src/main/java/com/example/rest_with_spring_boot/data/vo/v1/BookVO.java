package com.example.rest_with_spring_boot.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.*;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
@JsonPropertyOrder({"id", "author", "launch_date", "price", "title"})
@EqualsAndHashCode
@NoArgsConstructor
@Getter @Setter
public class BookVO extends RepresentationModel<BookVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private int key;


    private String author;

    @JsonProperty("launch_date")
    private Date launchDate;

    private double price;
    private String title;

}
