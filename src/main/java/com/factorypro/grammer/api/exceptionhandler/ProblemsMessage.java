package com.factorypro.grammer.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class ProblemsMessage {
	
	private Integer status;
	private OffsetDateTime dateHour;
	private String title;
	private List<Field> field;
	
	
	@AllArgsConstructor
	@Getter
	public static class Field{
		
		private String name;
		private String message;
	}

}
