package com.factorypro.grammer.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryRequestModel {

	@Valid
	@NotNull
	private ClientIDInput client;
	
	@Valid
	@NotNull
	private RecipientInput recipient;
	
	@NotNull
	private BigDecimal rate;
	
	
}
