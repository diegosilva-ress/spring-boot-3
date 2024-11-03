package br.com.services.impl;

import br.com.repository.UserRepository;
import br.com.services.UserService;
import java.util.logging.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository userRepository;

  private final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    logger.info("Finding one user by name " + username + "!");
    var user = userRepository.findByUsername(username);

    if(user == null) {
      return user;
    } else {
      throw new UsernameNotFoundException("Username " + username + " not found!");
    }

  }
}
