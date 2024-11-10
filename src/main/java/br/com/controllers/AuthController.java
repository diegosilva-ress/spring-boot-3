package br.com.controllers;

import br.com.data.vo.security.AccountCredentialsVO;
import br.com.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @SuppressWarnings("rawtypes")
  @Operation(summary = "Authenticates a user and returns a token")
  @PostMapping(value = "/signin")
  public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
    var token = authService.signin(data);
    if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
    return token;
  }

  @SuppressWarnings("rawtypes")
  @Operation(summary = "Refresh token for authenticated user and returns a token")
  @PutMapping(value = "/refresh/{username}")
  public ResponseEntity refreshToken(@PathVariable("username") String username,
      @RequestHeader("Authorization") String refreshToken) {
    var token = authService.refreshToken(username, refreshToken);
    if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
    return token;
  }

}
