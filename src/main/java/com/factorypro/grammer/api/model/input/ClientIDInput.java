package com.factorypro.grammer.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientIDInput {

	@NotNull
	private Long id;
}
