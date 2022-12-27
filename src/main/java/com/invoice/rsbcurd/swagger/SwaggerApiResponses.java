package com.invoice.rsbcurd.swagger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successfully retrieved data"),
		@ApiResponse(responseCode = "204", description = "No content - Record not found"),
		@ApiResponse(responseCode = "401", description = "You are not authorized"),
		@ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(responseCode = "404", description = "The resource you trying to reach is not found"),
		@ApiResponse(responseCode = "409", description = "A conflict occured while trying to access the resource"),
		@ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR") })
public @interface SwaggerApiResponses {

}
