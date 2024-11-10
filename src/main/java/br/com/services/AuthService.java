package br.com.services;

import br.com.data.vo.security.AccountCredentialsVO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

  ResponseEntity signin(AccountCredentialsVO data);

}
