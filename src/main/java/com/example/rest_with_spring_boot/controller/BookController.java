package com.example.rest_with_spring_boot.controller;

import com.example.rest_with_spring_boot.data.vo.v1.BookVO;
import com.example.rest_with_spring_boot.services.BookServices;
import com.example.rest_with_spring_boot.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book")
@Tag(name = "Book", description = "Endpoints for Book Management")
public class BookController {

    @Autowired
    private BookServices services;

    @GetMapping(value = "/v1/{id}",
            consumes = { MediaType.APPLICATION_JSON ,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML })
    @Operation(summary = "Finds a book", description = "Find a book recorded in the database",
            tags = {"Book"},
            responses = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = BookVO.class))),
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public BookVO findById(@PathVariable(value = "id") int id) {
        return services.findById(id);
    }

    @GetMapping(value = "/v1",
            consumes = { MediaType.APPLICATION_JSON ,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML })
    @Operation(summary = "Finds all books", description = "Find all books recorded in the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    public List<BookVO> findAll() {
        return services.findAll();
    }

    @PostMapping(value = "/v1",
            consumes = { MediaType.APPLICATION_JSON ,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML })
    @Operation(summary = "Creates a book", description = "Create a book in the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    public BookVO create(@RequestBody BookVO book) {
        return services.create(book);
    }

    @PutMapping(value = "/v1",
            consumes = { MediaType.APPLICATION_JSON ,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML },
            produces = { MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YAML })
    @Operation(summary = "Updates a book", description = "Update a book recorded in the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    public BookVO update(@RequestBody BookVO book) {
        return services.update(book);
    }

    @DeleteMapping(value = "/v1/{id}")
    @Operation(summary = "Deletes a book", description = "Delete a book recorded in the database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = BookVO.class))),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") int id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

}
