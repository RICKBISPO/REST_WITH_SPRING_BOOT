package com.example.rest_with_spring_boot.controller;

import com.example.rest_with_spring_boot.data.vo.v1.PersonVO;
import com.example.rest_with_spring_boot.data.vo.v2.PersonVOV2;
import com.example.rest_with_spring_boot.services.PersonServices;
import com.example.rest_with_spring_boot.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person")
@Tag(name = "People", description = "Endpoints for People Management")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping(value = "/v1/{id}",
            produces = { MediaType.APPLICATION_JSON ,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML })
    @Operation(summary = "Finds a person", description = "Find a person recorded in the database",
            tags = {"People"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return services.findById(id);
    }

    @GetMapping(value = "/v1",
                produces = { MediaType.APPLICATION_JSON ,
                            MediaType.APPLICATION_XML,
                            MediaType.APPLICATION_YAML })
    @Operation(summary = "Finds all people", description = "Find all people recorded in the database",
                tags = {"People"},
                responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = {
                                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))),
                            }),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
                })
    public List<PersonVO> findAll() {
        return services.findAll();
    }

    @PostMapping(value = "/v1",
            consumes = { MediaType.APPLICATION_JSON ,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML })
    @Operation(summary = "Creates a person", description = "Create a person in the database",
            tags = {"People"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    public PersonVO create(@RequestBody PersonVO person) {
        return services.create(person);
    }

    @PostMapping(value = "/v2",
            consumes = { MediaType.APPLICATION_JSON ,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML })
    @Operation(summary = "Creates a person (V2)", description = "Create a person in the database (V2)",
            tags = {"People"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = PersonVOV2.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
        return services.createV2(person);
    }

    @PutMapping(value = "/v1",
            consumes = { MediaType.APPLICATION_JSON ,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML })
    @Operation(summary = "Updates a person", description = "Update a person recorded in the database",
            tags = {"People"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    public PersonVO update(@RequestBody PersonVO person) {
        return services.update(person);
    }

    @DeleteMapping(value = "/v1/{id}")
    @Operation(summary = "Deletes a person", description = "Delete a person recorded in the database",
            tags = {"People"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
