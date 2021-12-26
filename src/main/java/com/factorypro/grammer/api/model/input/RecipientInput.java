package com.factorypro.grammer.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipientInput {

	@NotBlank	
	private String name;
	
	@NotBlank	
	private String street;
	
	@NotBlank	
	private String number;
	
	@NotBlank	
	private String complement;
	
	@NotBlank	
	private String district;
}
