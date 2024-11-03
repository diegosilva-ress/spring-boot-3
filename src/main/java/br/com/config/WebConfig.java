package br.com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Value("${cors.originPatterns:default}")
  private String corsOriginPatterns = "";

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    //  via queryparam
//    configurer.favorParameter(true);
//    configurer.parameterName("mediaType").ignoreAcceptHeader(true).useRegisteredExtensionsOnly(true)
//        .defaultContentType(MediaType.APPLICATION_JSON)
//        .mediaType("json", MediaType.APPLICATION_JSON)
//        .mediaType("xml", MediaType.APPLICATION_XML);

    //via header
    configurer.favorParameter(false);
    configurer.ignoreAcceptHeader(false).useRegisteredExtensionsOnly(false)
        .defaultContentType(MediaType.APPLICATION_JSON)
        .mediaType("json", MediaType.APPLICATION_JSON)
        .mediaType("xml", MediaType.APPLICATION_XML);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    var allowedOrigins = corsOriginPatterns.split(",");
    registry.addMapping("/**")
//        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedMethods("*")
        .allowedOrigins(allowedOrigins)
        .allowCredentials(true);
  }
}
