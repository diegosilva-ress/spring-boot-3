package br.com.controllers;

import br.com.data.vo.PersonVO;
import br.com.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @CrossOrigin(origins = "http://localhost:8080")
  @GetMapping(value = "/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE,
          MediaType.APPLICATION_XML_VALUE})
  @Operation(summary = "Finds a person", description = "Finds a person",
      tags = {"People"}, responses = {
      @ApiResponse(description = "Success", responseCode = "200", content = {
          @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonVO.class))
      }),
      @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
  })
  public PersonVO findById(@PathVariable("id") Long id) {
    return this.personService.findById(id);
  }

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @Operation(summary = "Finds all people", description = "Finds all people",
      tags = {"People"}, responses = {
      @ApiResponse(description = "Success", responseCode = "200", content = {
          @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
      }),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
  })
  public ResponseEntity<PagedModel<EntityModel<PersonVO>>> findAll(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "size", defaultValue = "12") Integer size,
      @RequestParam(value = "direction", defaultValue = "asc") String direction) {

    var sortDirection = direction.equalsIgnoreCase("asc") ? Direction.ASC : Direction.DESC;
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "firstName"));
    return ResponseEntity.ok(this.personService.findAll(pageable));
  }

  @CrossOrigin(origins = {"http://localhost:8080"})
  @PostMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @Operation(summary = "Add a new person", description = "Add a new person",
      tags = {"People"}, responses = {
      @ApiResponse(description = "Success", responseCode = "200", content = {
          @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonVO.class))
      }),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
  })
  public PersonVO create(@RequestBody PersonVO personVO) {
    return this.personService.create(personVO);
  }

  @PutMapping(
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @Operation(summary = "Updates a person", description = "Updates a person",
      tags = {"People"}, responses = {
      @ApiResponse(description = "Success", responseCode = "200", content = {
          @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonVO.class))
      }),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
  })
  public PersonVO update(@RequestBody PersonVO personVO) {
    return this.personService.update(personVO);
  }

  @DeleteMapping(value = "/{id}")
  @Operation(summary = "Deletes a person", description = "Deletes a person",
      tags = {"People"}, responses = {
      @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
  })
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    this.personService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping(value = "/{id}",
      produces = {MediaType.APPLICATION_JSON_VALUE,
          MediaType.APPLICATION_XML_VALUE})
  @Operation(summary = "Disable a specific person by id", description = "Disable a specific person by id",
      tags = {"People"}, responses = {
      @ApiResponse(description = "Success", responseCode = "200", content = {
          @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PersonVO.class))
      }),
      @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
      @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
      @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
      @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
      @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
  })
  public PersonVO disablePerson(@PathVariable("id") Long id) {
    return this.personService.disablePerson(id);
  }

}
