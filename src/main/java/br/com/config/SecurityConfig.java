package br.com.config;

import br.com.security.JwtTokenProvider;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfiguration {

  private JwtTokenProvider jwtTokenProvider;

  public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    Map<String, PasswordEncoder> encoders = new HashMap<>();
    encoders.put("pbkdf2", new DelegatingPasswordEncoder("pbkdf2", new Pbkdf2PasswordEncoder()));
    DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
    passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());

    return passwordEncoder;
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic().disable()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(
            "/auth/signin",
            "/auth/refresh",
            "/api-docs/**",
            "/swagger-ui.html**"
        ).permitAll()
        .antMatchers("/api/**").authenticated()
        .antMatchers("/users").denyAll()
        .and()
        .cors()
        .and()
        .apply(new JwtConfigurer(tokenProvider));
  }



}
