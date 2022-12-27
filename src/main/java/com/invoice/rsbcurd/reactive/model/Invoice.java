package com.invoice.rsbcurd.reactive.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Generated
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
	
	private Integer id;
	@NonNull
	private String name;
	@NonNull
	private String number;
	@NonNull
	private Double amount;
}
