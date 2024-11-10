package br.com.services.impl;

import br.com.data.vo.security.AccountCredentialsVO;
import br.com.data.vo.security.TokenVO;
import br.com.repository.UserRepository;
import br.com.security.JwtTokenProvider;
import br.com.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  private JwtTokenProvider tokenProvider;

  private AuthenticationManager authenticationManager;

  private UserRepository userRepository;

  public AuthServiceImpl(JwtTokenProvider tokenProvider,
      AuthenticationManager authenticationManager,
      UserRepository userRepository) {
    this.tokenProvider = tokenProvider;
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
  }

  public ResponseEntity signin(AccountCredentialsVO data) {
    try {
      var username = data.getUsername();
      var password = data.getPassword();

      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

      var user = userRepository.findByUsername(username);

      var tokenResponse = new TokenVO();
      if (user != null) {
        tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
      } else {
        throw new UsernameNotFoundException("Username " + username + " not found!");
      }

      return ResponseEntity.ok(tokenResponse);
    } catch (Exception e) {
      throw new BadCredentialsException("Invalid username/password suplied!");
    }
  }

}
