package br.com.security;

import br.com.data.vo.security.TokenVO;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class JwtTokenProvider {

  @Value("${security.jwt.token.secret-key:secret}")
  private String secretKey = "secret";

  @Value("${security.jwt.token.expire-length:secret}")
  private long validityInMilliseconds = 3600000;

  private UserDetailsService userDetailsService;

  Algorithm algorithm = null;

  public JwtTokenProvider(UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    algorithm = Algorithm.HMAC256(secretKey);
  }

  public TokenVO createAccessToken(String username, List<String> roles) {
    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    var accessToken = getAccessToken(username, roles, now, validity);
    var refreshToken = getAccessToken(username, roles, now);

    return new TokenVO(username, true, now, validity, accessToken, refreshToken);
  }

  private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
    return null;
  }

  private String getAccessToken(String username, List<String> roles, Date now) {
    return null;
  }

}
