package br.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
//
//		 Map<String, PasswordEncoder> encoders = new HashMap<>();
//
//		 Pbkdf2PasswordEncoder pbkdf2Encoder =
//		 new Pbkdf2PasswordEncoder(
//		 "", 8, 185000,
//		 SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
//
//		 encoders.put("pbkdf2", pbkdf2Encoder);
//		 DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
//		 passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
//
//		 String result1 = passwordEncoder.encode("diego123");
//		 System.out.println("My hash result1 " + result1);
	}

}
