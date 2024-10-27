package br.com.controllers;

import br.com.data.vo.BookVO;
import br.com.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books/v1")
@Tag(name = "Book", description = "Endpoints for Managing Books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping(value = "/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE,
          MediaType.APPLICATION_XML_VALUE})
  @Operation(summary = "Finds a book", description = "Finds a book",
      tags = {"Book"}, responses = {
      @ApiResponse(description = "Success", responseCode = "200", content = {
          @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BookVO.class))
      }),
      @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
  })
  public BookVO findById(@PathVariable("id") Long id) {
    return this.bookService.findById(id);
  }

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @Operation(summary = "Finds all books", description = "Finds all books",
      tags = {"Book"}, responses = {
      @ApiResponse(description = "Success", responseCode = "200", content = {
          @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))
      }),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
  })
  public List<BookVO> findAll() {
    return this.bookService.findAll();
  }

  @PostMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @Operation(summary = "Add a new book", description = "Add a new book",
      tags = {"Book"}, responses = {
      @ApiResponse(description = "Success", responseCode = "200", content = {
          @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BookVO.class))
      }),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
  })
  public BookVO create(@RequestBody BookVO bookVO) {
    return this.bookService.create(bookVO);
  }

  @PutMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @Operation(summary = "Updates a book", description = "Updates a book",
      tags = {"Book"}, responses = {
      @ApiResponse(description = "Success", responseCode = "200", content = {
          @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BookVO.class))
      }),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
  })
  public BookVO update(@RequestBody BookVO bookVO) {
    return this.bookService.update(bookVO);
  }

  @DeleteMapping(value = "/{id}")
  @Operation(summary = "Deletes a book", description = "Deletes a book",
      tags = {"Book"}, responses = {
      @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
  })
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    this.bookService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
