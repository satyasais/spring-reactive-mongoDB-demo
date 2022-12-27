package com.invoice.rsbcurd.config;



import static com.invoice.rsbcurd.constants.SwaggerConstant.API_DESCRIPTION;
import static com.invoice.rsbcurd.constants.SwaggerConstant.API_TITLE;
import static com.invoice.rsbcurd.constants.SwaggerConstant.API_VERSION;
import static com.invoice.rsbcurd.constants.SwaggerConstant.CONTACT_EMAIL;
import static com.invoice.rsbcurd.constants.SwaggerConstant.CONTACT_NAME;
import static com.invoice.rsbcurd.constants.SwaggerConstant.CONTACT_URL;
import static com.invoice.rsbcurd.constants.SwaggerConstant.LICENSE;
import static com.invoice.rsbcurd.constants.SwaggerConstant.LICENSE_URL;
import static com.invoice.rsbcurd.constants.SwaggerConstant.TERM_OF_SERVICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * This class is used for managing Swagger API configuration
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	 public static final String AUTHORIZATION_HEADER = "Authorization";
	  
	    private ApiInfo apiInfo() {
	        return new ApiInfo(API_TITLE, API_DESCRIPTION, API_VERSION, TERM_OF_SERVICE, contact(),
	                LICENSE, LICENSE_URL, Collections.emptyList());
	    }

	    private Contact contact() {
	        return new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL);
	    }

	    @Bean
	       public Docket api() { 
	           return new Docket(DocumentationType.SWAGGER_2)
	        	  .apiInfo(apiInfo())
	        		      .securityContexts(Arrays.asList(securityContext()))
	        		      .securitySchemes(Arrays.asList(apiKey()))
	             .select()                                  
	             .apis(RequestHandlerSelectors.any())              
	             .paths(PathSelectors.any())                          
	             .build();                                           
	       }
		   
		   
		   private ApiKey apiKey() { 
			    return new ApiKey("JWT", AUTHORIZATION_HEADER, "header"); 
			}
		   
		   private List<SecurityReference> defaultAuth() { 
			    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
			    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
			    authorizationScopes[0] = authorizationScope; 
			    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
			}
		   
		   private SecurityContext securityContext() { 
			    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
			}

}
